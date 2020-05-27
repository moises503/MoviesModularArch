package com.moises.core.arch.tasking.observer

import io.reactivex.observers.DisposableMaybeObserver

abstract class MaybeObserver<T> : DisposableMaybeObserver<T>() {
    override fun onComplete() = Unit
    override fun onSuccess(t: T) = Unit
    override fun onError(e: Throwable) = Unit
}