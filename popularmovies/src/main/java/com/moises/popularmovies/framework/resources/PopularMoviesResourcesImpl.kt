package com.moises.popularmovies.framework.resources

import android.content.Context
import com.moises.popularmovies.R

class PopularMoviesResourcesImpl(private val context: Context) : PopularMoviesResources {
    override fun popularMoviesErrorMessage(): String {
        return context.getString(R.string.popular_movies_error_message)
    }
}