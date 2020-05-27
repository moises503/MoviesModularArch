package com.moises.core.database

import android.content.Context
import androidx.room.Room
import com.moises.core.constants.Constants.MOVIES_DATABASE_NAME

class DatabaseBuilder {
    fun buildDatabase(app : Context) : MoviesDatabase = Room.databaseBuilder(app,
        MoviesDatabase::class.java, MOVIES_DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}