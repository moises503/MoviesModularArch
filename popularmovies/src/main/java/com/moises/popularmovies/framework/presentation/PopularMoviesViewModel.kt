package com.moises.popularmovies.framework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moises.core.arch.tasking.observer.MaybeObserver
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
        getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Params(page))
            .doOnSubscribe {
                _popularMoviesState.postValue(ScreenState.Loading)
            }.subscribeWith(PopularMoviesObserver()).addTo(compositeDisposable)
    }

    private inner class PopularMoviesObserver : MaybeObserver<List<PopularMovie>>() {

        override fun onSuccess(t: List<PopularMovie>) {
            _popularMoviesState.postValue(ScreenState.Render(PopularMoviesScreenState.Movies(t)))
        }

        override fun onError(e: Throwable) {
            _popularMoviesState.postValue(
                ScreenState.Render(
                    PopularMoviesScreenState.Error(
                        popularMoviesResources.popularMoviesErrorMessage()
                    )
                )
            )
        }
    }
}