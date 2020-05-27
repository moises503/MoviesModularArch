package com.moises.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.core.database.entity.PopularMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PopularMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPopularMovies(movies: List<PopularMovieEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAPopularMovie(movie: PopularMovieEntity): Completable

    @Query("SELECT * FROM popular_movies")
    fun selectAllPopularMovies(): Single<List<PopularMovieEntity>>

    @Query("SELECT * FROM popular_movies WHERE page = (:page)")
    fun selectAllPopularMoviesBy(page: Int): Single<List<PopularMovieEntity>>

}