package com.moises.popularmovies.framework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moises.core.arch.tasking.observer.MaybeObserver
import com.moises.core.constants.Constants.MAX_PAGES
import com.moises.core.ui.BaseViewModel
import com.moises.core.ui.ScreenState
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.domain.usecase.GetPopularMoviesUseCase
import com.moises.popularmovies.framework.resources.PopularMoviesResources
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMoviesResources: PopularMoviesResources
) : BaseViewModel() {
    private var _popularMoviesState = MutableLiveData<ScreenState<PopularMoviesScreenState>>()
    val popularMoviesState: LiveData<ScreenState<PopularMoviesScreenState>>
        get() =
            _popularMoviesState
    var page: Int = 1

    fun retrieveAllPopularMovies() {
        when {
            canLoadMoreMovies(page) -> sendPopularMoviesRequest()
            else -> _popularMoviesState.postValue(
                ScreenState.Render(
                    PopularMoviesScreenState.Error(
                        popularMoviesResources.popularMoviesErrorMessage()
                    )
                )
            )
        }

    }

    private fun sendPopularMoviesRequest() {
        getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Params(page))
            .doOnSubscribe {
                _popularMoviesState.postValue(ScreenState.Loading)
            }.subscribeWith(PopularMoviesObserver()).addTo(compositeDisposable)
    }

    private fun canLoadMoreMovies(currentPage: Int) = currentPage < MAX_PAGES

    private inner class PopularMoviesObserver : MaybeObserver<List<PopularMovie>>() {

        override fun onSuccess(t: List<PopularMovie>) {
            page++
            _popularMoviesState.postValue(ScreenState.Render(PopularMoviesScreenState.Movies(t)))
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
            _popularMoviesState.postValue(
                ScreenState.Render(
                    PopularMoviesScreenState.Error(
                        e.localizedMessage ?: popularMoviesResources.popularMoviesErrorMessage()
                    )
                )
            )
        }
    }
}