package com.moises.popularmovies.framework.datasource.endpoint

import com.moises.core.constants.Constants.API_KEY
import com.moises.core.constants.Constants.DEFAULT_LANGUAGE
import com.moises.core.constants.Constants.DEFAULT_REGION
import com.moises.popularmovies.framework.datasource.model.PopularMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesEndPoint {
    @GET("movie/popular")
    fun retrieveAllPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("region") region: String = DEFAULT_REGION
    ): Single<PopularMoviesResponse>
}