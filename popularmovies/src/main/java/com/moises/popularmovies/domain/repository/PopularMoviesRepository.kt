package com.moises.popularmovies.domain.repository

import com.moises.popularmovies.domain.model.PopularMovie
import io.reactivex.Maybe

interface PopularMoviesRepository {
    fun retrieveAllPopularMovies(page : Int) : Maybe<List<PopularMovie>>
}