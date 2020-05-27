package com.moises.popularmovies.data.repository

import com.moises.popularmovies.data.datasource.local.PopularMoviesLocalDataSource
import com.moises.popularmovies.data.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.domain.repository.PopularMoviesRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesLocalDataSource: PopularMoviesLocalDataSource,
    private val popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
) : PopularMoviesRepository {
    override fun retrieveAllPopularMovies(page: Int): Maybe<List<PopularMovie>> {
        return Single.concat(popularMoviesLocalDataSource.retrieveAllPopularMoviesFromDatabaseBy(page),
            popularMoviesRemoteDataSource.retrieveAllPopularMoviesFromServer(page)).filter { popularMovies ->
            popularMovies.isNotEmpty()
        }.firstElement().doOnSuccess { popularMovies ->
            popularMovies.forEach {
                it.page = page
                popularMoviesLocalDataSource.saveAPopularMovie(it)
            }
        }.doOnError {
            println("An error has occurred when it wanted to retrieve information ${it.message}")
            it.printStackTrace()
        }
    }
}