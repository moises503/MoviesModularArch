package com.moises.popularmovies.data.datasource.remote

import com.moises.popularmovies.domain.model.PopularMovie
import io.reactivex.Single

interface PopularMoviesRemoteDataSource {
    fun retrieveAllPopularMoviesFromServer(page : Int) : Single<List<PopularMovie>>
}