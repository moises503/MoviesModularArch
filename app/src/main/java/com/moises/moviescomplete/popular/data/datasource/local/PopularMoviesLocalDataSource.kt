package com.moises.moviescomplete.popular.data.datasource.local

import com.moises.moviescomplete.popular.domain.model.PopularMovie

interface PopularMoviesLocalDataSource {
     fun retrieveAllPopularMoviesFromDatabase(page : Int) : List<PopularMovie>
}