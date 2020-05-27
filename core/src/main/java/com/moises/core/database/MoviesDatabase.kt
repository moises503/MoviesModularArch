package com.moises.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moises.core.constants.Constants.MOVIES_DATABASE_VERSION
import com.moises.core.database.dao.PopularMoviesDao
import com.moises.core.database.entity.PopularMovieEntity

@Database(entities = [PopularMovieEntity::class], version = MOVIES_DATABASE_VERSION, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun popularMoviesDao() : PopularMoviesDao
}