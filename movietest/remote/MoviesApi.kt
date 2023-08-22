package com.example.movietest.remote

import com.example.movietest.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MoviesApi {
    @GET("/")
    suspend fun getMovies(
        @QueryMap queries:Map<String,String>
    ): Response<MovieModel>
}