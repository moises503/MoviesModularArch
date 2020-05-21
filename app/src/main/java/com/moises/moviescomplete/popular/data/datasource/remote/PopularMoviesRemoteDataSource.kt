package com.moises.moviescomplete.popular.data.datasource.remote

import com.moises.moviescomplete.popular.domain.model.PopularMovie

interface PopularMoviesRemoteDataSource {
    fun retrieveAllPopularMoviesFromServer(page : Int) : List<PopularMovie>
}