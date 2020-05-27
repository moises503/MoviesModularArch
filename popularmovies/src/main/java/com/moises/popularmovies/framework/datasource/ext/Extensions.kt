package com.moises.popularmovies.framework.datasource.ext

import com.moises.core.database.entity.PopularMovieEntity
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.framework.datasource.mapper.FromPopularMovieDataToDomain
import com.moises.popularmovies.framework.datasource.mapper.FromPopularMovieEntityToDomain

val fromPopularMovieEntityToDomain = FromPopularMovieEntityToDomain()

fun List<PopularMovie>.toListEntityModel(): List<PopularMovieEntity> {
    return fromPopularMovieEntityToDomain.reverseTransformCollection(this)
}

fun List<PopularMovieEntity>.toListDomainModel(): List<PopularMovie> {
    return fromPopularMovieEntityToDomain.transformCollection(this)
}

fun PopularMovie.toEntityModel(): PopularMovieEntity {
    return fromPopularMovieEntityToDomain.reverseTransform(this)
}

fun PopularMovieEntity.toDomainModel(): PopularMovie {
    return fromPopularMovieEntityToDomain.transform(this)
}

fun List<com.moises.popularmovies.framework.datasource.model.PopularMovie>.toListPopularMoviesModel(): List<PopularMovie> {
    return FromPopularMovieDataToDomain().transformCollection(this)
}