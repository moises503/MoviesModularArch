package com.moises.moviescomplete.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moises.moviescomplete.database.MoviesDatabase.Companion.MOVIES_DATABASE_VERSION
import com.moises.moviescomplete.database.entity.PopularMovieEntity

@Database(entities = [PopularMovieEntity::class], version = MOVIES_DATABASE_VERSION, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    companion object {
        const val MOVIES_DATABASE_NAME = "movies"
        const val MOVIES_DATABASE_VERSION = 1
    }
}