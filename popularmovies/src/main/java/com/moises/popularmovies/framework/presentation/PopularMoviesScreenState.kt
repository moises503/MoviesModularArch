package com.moises.popularmovies.framework.presentation

import com.moises.popularmovies.domain.model.PopularMovie


sealed class PopularMoviesScreenState {
    class Error(val message : String) : PopularMoviesScreenState()
    class Movies(val list : List<PopularMovie>) : PopularMoviesScreenState()
}