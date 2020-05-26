package com.moises.moviescomplete.popular.domain.model

data class PopularMovie(
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Int,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int
)