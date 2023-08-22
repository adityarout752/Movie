package com.example.movietest.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movietest.model.MovieModel

@Entity(tableName = "movies_table")
class MovieEntity(
    var movie: MovieModel
) {
    @PrimaryKey(autoGenerate = false)
    var id:Int =0
}