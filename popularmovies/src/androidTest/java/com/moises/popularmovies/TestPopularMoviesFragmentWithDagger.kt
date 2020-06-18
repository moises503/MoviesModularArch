package com.moises.popularmovies

import com.moises.popularmovies.framework.di.PopularMoviesComponent
import com.moises.popularmovies.framework.ui.PopularMoviesFragment

class TestPopularMoviesFragmentWithDagger : PopularMoviesFragment() {

    override fun setupInjection() {
        popularMoviesComponent.inject(this)
    }

    companion object {
        lateinit var popularMoviesComponent: PopularMoviesComponent
    }
}