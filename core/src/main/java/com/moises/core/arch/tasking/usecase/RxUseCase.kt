package com.moises.core.arch.tasking.usecase

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