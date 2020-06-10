package com.moises.popularmovies.framework.datasource.local

import com.moises.core.database.dao.PopularMoviesDao
import com.moises.popularmovies.data.datasource.local.PopularMoviesLocalDataSource
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.framework.datasource.ext.toEntityModel
import com.moises.popularmovies.framework.datasource.ext.toListDomainModel
import com.moises.popularmovies.framework.datasource.ext.toListEntityModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PopularMoviesLocalDataSourceImpl @Inject constructor(private val popularMoviesDao: PopularMoviesDao) :
    PopularMoviesLocalDataSource {

    override fun retrieveAllPopularMoviesFromDatabase(): Single<List<PopularMovie>> =
        popularMoviesDao.selectAllPopularMovies().map { popularMovieEntities ->
            popularMovieEntities.toListDomainModel()
        }

    override fun saveAllPopularMovies(movies: List<PopularMovie>) {
        popularMoviesDao.insertPopularMovies(movies.toListEntityModel())
    }

    override fun saveAPopularMovie(movie: PopularMovie) : Completable {
        return popularMoviesDao.insertAPopularMovie(movie.toEntityModel())
    }

    override fun retrieveAllPopularMoviesFromDatabaseBy(page: Int): Single<List<PopularMovie>> =
        popularMoviesDao.selectAllPopularMoviesBy(page).map { popularMovieEntities ->
            popularMovieEntities.toListDomainModel()
        }
}