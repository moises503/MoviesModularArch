package com.moises.popularmovies

import com.moises.popularmovies.domain.model.PopularMovie

class FakerClass {

    fun popularMovies(): List<PopularMovie> {
        val popularMovies: MutableList<PopularMovie> = mutableListOf()
        for (i in 1..3) {
            popularMovies.add(
                PopularMovie(
                    overview = "overview $i",
                    originalLanguage = "language $i",
                    originalTitle = "title $i",
                    video = true,
                    title = "title $i",
                    posterPath = "poster $i",
                    backdropPath = "backdrop $i",
                    releaseDate = "20/10/2020",
                    popularity = i.toDouble(),
                    voteAverage = i.toDouble(),
                    id = i,
                    adult = true,
                    voteCount = i,
                    page = 1,
                    isFavorite = true
                )
            )
        }
        return popularMovies.toList()
    }
}