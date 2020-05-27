package com.moises.core.di

import android.content.Context
import com.moises.core.database.DatabaseBuilder
import com.moises.core.database.MoviesDatabase
import com.moises.core.database.dao.PopularMoviesDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val context: Context) {
    @Provides
    fun providesMovieDatabase() : MoviesDatabase {
        return DatabaseBuilder().buildDatabase(context)
    }

    @Provides
    fun providesPopularMoviesDao(moviesDatabase: MoviesDatabase): PopularMoviesDao {
        return moviesDatabase.popularMoviesDao()
    }
}