package com.moises.popularmovies.framework.datasource.mapper

import com.moises.core.arch.mapper.Transformer
import com.moises.core.database.entity.PopularMovieEntity
import com.moises.popularmovies.framework.datasource.model.PopularMovie

class FromPopularMovieDataToEntity : Transformer<PopularMovie, PopularMovieEntity>() {
    override fun transform(value: PopularMovie): PopularMovieEntity {
        return PopularMovieEntity(
            overview = value.overview.orEmpty(),
            originalLanguage = value.originalLanguage.orEmpty(),
            originalTitle = value.originalTitle.orEmpty(),
            video = value.video ?: false,
            title = value.title.orEmpty(),
            posterPath = value.posterPath.orEmpty(),
            backdropPath = value.backdropPath.orEmpty(),
            releaseDate = value.releaseDate.orEmpty(),
            popularity = value.popularity ?: 0.0,
            voteAverage = value.voteAverage ?: 0.0,
            id = value.id ?: 0,
            adult = value.adult ?: false,
            voteCount = value.voteCount ?: 0,
            page = 0,
            isFavorite = false
        )
    }
}