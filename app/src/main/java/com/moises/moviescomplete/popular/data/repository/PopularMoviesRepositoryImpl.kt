package com.moises.moviescomplete.popular.data.repository

import com.moises.moviescomplete.popular.data.datasource.local.PopularMoviesLocalDataSource
import com.moises.moviescomplete.popular.data.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.moviescomplete.popular.domain.model.PopularMovie
import com.moises.moviescomplete.popular.domain.repository.PopularMoviesRepository

class PopularMoviesRepositoryImpl(
    private val popularMoviesLocalDataSource: PopularMoviesLocalDataSource,
    private val popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
) : PopularMoviesRepository {
    override fun retrieveAllPopularMovies(page: Int): List<PopularMovie> {
        TODO("You must implement this method for retrieving all popular movies") //To change body of created functions use File | Settings | File Templates.
    }
}