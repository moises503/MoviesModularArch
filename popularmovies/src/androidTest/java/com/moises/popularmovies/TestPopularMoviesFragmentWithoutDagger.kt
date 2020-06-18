package com.moises.popularmovies

import com.moises.popularmovies.framework.presentation.PopularMoviesViewModel
import com.moises.popularmovies.framework.ui.PopularMoviesFragment

class TestPopularMoviesFragmentWithoutDagger : PopularMoviesFragment() {

    override fun setupInjection() = Unit

    override fun bindViewModel() {
        this.popularMoviesViewModel = popularMoviesViewModelTest
    }

    companion object {
        lateinit var popularMoviesViewModelTest: PopularMoviesViewModel
    }
}