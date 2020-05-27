package com.moises.popularmovies.data.datasource.local

import com.moises.popularmovies.domain.model.PopularMovie
import io.reactivex.Single

interface PopularMoviesLocalDataSource {
     fun retrieveAllPopularMoviesFromDatabase() : Single<List<PopularMovie>>
     fun saveAllPopularMovies(movies : List<PopularMovie>)
     fun saveAPopularMovie(movie : PopularMovie)
     fun retrieveAllPopularMoviesFromDatabaseBy(page : Int) : Single<List<PopularMovie>>
}