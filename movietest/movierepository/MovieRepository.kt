package com.example.movietest.movierepository

import androidx.lifecycle.LiveData
import com.example.movietest.db.MovieDao
import com.example.movietest.db.MovieEntity
import com.example.movietest.model.MovieModel
import com.example.movietest.remote.MoviesApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MovieRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val movieDao: MovieDao
) {
    suspend fun getMovies(
        quaries: Map<String, String>
    ): Response<MovieModel> {

        return moviesApi.getMovies(quaries)
    }

    fun readMovies(): LiveData<List<MovieEntity>> {
        return movieDao.readMovies()
    }

    suspend fun insertMovies(movieEntity: MovieEntity) {
        return movieDao.insertMovies(movieEntity)
    }
}