package com.example.movietest.moviewviewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movietest.db.MovieEntity
import com.example.movietest.model.MovieModel
import com.example.movietest.movierepository.MovieRepository
import com.example.movietest.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(application: Application,
                                        private val repository: MovieRepository
) :AndroidViewModel(application){

    val readMovies: LiveData<List<MovieEntity>> =repository.readMovies()
   private var movieResponse: MutableLiveData<NetworkResult<MovieModel>> = MutableLiveData()

    fun getMovies(queries: Map<String, String>) = viewModelScope.launch {
        getaMoviesCall(queries)
    }
    @SuppressLint("SuspiciousIndentation")
    private suspend fun getaMoviesCall(queries: Map<String, String>) {
        movieResponse.value = NetworkResult.Loading()

            try {
                val response = repository.getMovies(queries)
                Log.d("movie response", "${response.body()}")
                movieResponse.value = handleMovieResponse(response)

                val movie = movieResponse.value?.data
                if (movie != null) {
                    offlineCache(movie)
                }
            } catch (e: Exception) {
                movieResponse.value = NetworkResult.Error("Movies Not Found")
            }

    }
    private fun handleMovieResponse(response: Response<MovieModel>): NetworkResult<MovieModel> {
        Log.d("queryresponse", "${response.body()}")
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                NetworkResult.Error("API Key Limited.")
            }
            response.isSuccessful -> {
                val movieDataResponse = response.body()
                NetworkResult.Success(movieDataResponse!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun offlineCache(movieModel: MovieModel) {
        val moviesEntity = MovieEntity(movieModel)
        insertMovies(moviesEntity)
    }
    fun insertMovies(movieEntity: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovies(movieEntity)
        }
    }
}
