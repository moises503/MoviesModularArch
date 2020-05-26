package com.moises.moviescomplete.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.moviescomplete.database.entity.PopularMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PopularMoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPopularMovies(movies : List<PopularMovieEntity>) : Completable

    @Query("SELECT * FROM popular_movies")
    fun selectAllPopularMovies() : Single<List<PopularMovieEntity>>
}