package com.moises.moviescomplete.core.arch.tasking

import io.reactivex.disposables.Disposable

abstract class RxUseCase<Params, Observable : Any> {

    private var disposable: Disposable? = null

    protected abstract fun buildUseCase(params: Params? = null): Observable

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    protected fun subscribe(subscription: Disposable? = null) {
        disposable = subscription
    }

}