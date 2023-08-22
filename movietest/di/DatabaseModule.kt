package com.example.movietest.di

import android.content.Context
import androidx.room.Room
import com.example.movietest.db.MovieDao
import com.example.movietest.db.MoviewDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) =
        Room.databaseBuilder(
            context,
            MoviewDatabase::class.java,
            "movies_table"
        ).build()


    @Singleton
    @Provides
    fun providesDao(database: MoviewDatabase): MovieDao {
       return database.moviesDao()
    }
}