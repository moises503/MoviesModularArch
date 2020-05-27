package com.moises.popularmovies.domain.usecase

import com.moises.core.arch.exception.NullParametersException
import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import com.moises.core.arch.tasking.usecase.MaybeUseCase
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.domain.repository.PopularMoviesRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : MaybeUseCase<GetPopularMoviesUseCase.Params, List<PopularMovie>>(uiScheduler, jobScheduler) {

    override fun buildUseCase(params: Params?): Maybe<List<PopularMovie>> {
       params?.let {
           return popularMoviesRepository.retrieveAllPopularMovies(it.page)
       } ?: throw NullParametersException()
    }

    data class Params(val page: Int)
}