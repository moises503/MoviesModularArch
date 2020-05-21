package com.moises.moviescomplete.popular.domain.usecase

import com.moises.moviescomplete.core.arch.exception.NullParametersException
import com.moises.moviescomplete.core.arch.tasking.SimpleUseCase
import com.moises.moviescomplete.popular.domain.model.PopularMovie
import com.moises.moviescomplete.popular.domain.repository.PopularMoviesRepository

class RetrieveAllPopularMovies(private val popularMoviesRepository: PopularMoviesRepository) :
    SimpleUseCase<RetrieveAllPopularMovies.Params, List<PopularMovie>>() {

    override fun execute(params: Params?): List<PopularMovie> {
        params?.let {
            return popularMoviesRepository.retrieveAllPopularMovies(it.page)
        } ?: throw NullParametersException()
    }
    data class Params(val page : Int)
}