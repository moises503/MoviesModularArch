package com.moises.moviescomplete.popular.domain.repository

import com.moises.moviescomplete.popular.domain.model.PopularMovie

interface PopularMoviesRepository {
    fun retrieveAllPopularMovies(page : Int) : List<PopularMovie>
}