package com.moises.popularmovies.framework.datasource.remote

import com.moises.popularmovies.data.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.framework.datasource.endpoint.PopularMoviesEndPoint
import com.moises.popularmovies.framework.datasource.ext.toListPopularMoviesModel
import io.reactivex.Single
import javax.inject.Inject

class PopularMoviesRemoteDataSourceImpl @Inject constructor(private val popularMoviesEndPoint: PopularMoviesEndPoint) :
    PopularMoviesRemoteDataSource {

    override fun retrieveAllPopularMoviesFromServer(page: Int): Single<List<PopularMovie>> =
        popularMoviesEndPoint.retrieveAllPopularMovies(page).map { popularMoviesResponse ->
            popularMoviesResponse.results.orEmpty().toListPopularMoviesModel()
        }
}