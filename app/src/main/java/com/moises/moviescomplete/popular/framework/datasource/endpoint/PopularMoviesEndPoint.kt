package com.moises.moviescomplete.popular.framework.datasource.endpoint

import com.moises.moviescomplete.popular.framework.datasource.model.PopularMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesEndPoint {
    @GET("/movie/popular")
    fun retrieveAllPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Single<PopularMoviesResponse>
}