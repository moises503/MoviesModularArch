package com.moises.popularmovies.framework.datasource.mapper

import com.moises.core.arch.mapper.Transformer
import com.moises.core.database.entity.PopularMovieEntity
import com.moises.popularmovies.domain.model.PopularMovie

class FromPopularMovieEntityToDomain : Transformer<PopularMovieEntity, PopularMovie>() {
    override fun transform(value: PopularMovieEntity): PopularMovie {
        return PopularMovie(
            overview = value.overview,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            video = value.video,
            title = value.title,
            posterPath = value.posterPath,
            backdropPath = value.backdropPath,
            releaseDate = value.releaseDate,
            popularity = value.popularity,
            voteAverage = value.voteAverage,
            id = value.id,
            adult = value.adult,
            voteCount = value.voteCount,
            page = value.page,
            isFavorite = value.isFavorite
        )
    }

    override fun reverseTransform(value: PopularMovie): PopularMovieEntity {
        return PopularMovieEntity(
            overview = value.overview,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            video = value.video,
            title = value.title,
            posterPath = value.posterPath,
            backdropPath = value.backdropPath,
            releaseDate = value.releaseDate,
            popularity = value.popularity,
            voteAverage = value.voteAverage,
            id = value.id,
            adult = value.adult,
            voteCount = value.voteCount,
            page = value.page,
            isFavorite = value.isFavorite
        )
    }
}