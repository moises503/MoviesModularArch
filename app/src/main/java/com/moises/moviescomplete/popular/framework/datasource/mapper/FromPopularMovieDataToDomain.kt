package com.moises.moviescomplete.popular.framework.datasource.mapper

import com.moises.moviescomplete.core.arch.mapper.Transformer
import com.moises.moviescomplete.popular.framework.datasource.model.PopularMovie

class FromPopularMovieDataToDomain :
    Transformer<PopularMovie, com.moises.moviescomplete.popular.domain.model.PopularMovie>() {
    override fun transform(value: PopularMovie): com.moises.moviescomplete.popular.domain.model.PopularMovie {
        return com.moises.moviescomplete.popular.domain.model.PopularMovie(
            overview = value.overview.orEmpty(),
            originalLanguage = value.originalLanguage.orEmpty(),
            originalTitle = value.originalTitle.orEmpty(),
            video = value.video ?: false,
            title = value.title.orEmpty(),
            posterPath = value.posterPath.orEmpty(),
            backdropPath = value.backdropPath.orEmpty(),
            releaseDate = value.releaseDate.orEmpty(),
            popularity = value.popularity ?: 0.0,
            voteAverage = value.voteAverage ?: 0,
            id = value.id ?: 0,
            adult = value.adult ?: false,
            voteCount = value.voteCount ?: 0
        )
    }
}