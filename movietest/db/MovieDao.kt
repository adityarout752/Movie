package com.example.movietest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    fun readMovies(): LiveData<List<MovieEntity>>
}