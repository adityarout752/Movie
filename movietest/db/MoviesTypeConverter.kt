package com.example.movietest.db

import androidx.room.TypeConverter
import com.example.movietest.model.MovieModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MoviesTypeConverter {


    var gson = Gson()

    @TypeConverter
    fun moviesModelToString(model: MovieModel): String {
        return gson.toJson(model)
    }

    @TypeConverter
    fun stringToMovieModel(data :String) : MovieModel {
        val listType = object : TypeToken<MovieModel>() {}.type
        return  gson.fromJson(data,listType)
    }
}