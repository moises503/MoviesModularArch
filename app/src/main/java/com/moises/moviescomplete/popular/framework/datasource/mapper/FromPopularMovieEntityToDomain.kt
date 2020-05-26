package com.moises.moviescomplete.popular.framework.datasource.mapper

import com.moises.moviescomplete.core.arch.mapper.Transformer
import com.moises.moviescomplete.database.entity.PopularMovieEntity
import com.moises.moviescomplete.popular.domain.model.PopularMovie

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
            voteCount = value.voteCount
        )
    }
}