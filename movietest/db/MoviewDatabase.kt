package com.example.movietest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MoviesTypeConverter::class)
abstract class MoviewDatabase : RoomDatabase() {

    abstract fun moviesDao() : MovieDao
}