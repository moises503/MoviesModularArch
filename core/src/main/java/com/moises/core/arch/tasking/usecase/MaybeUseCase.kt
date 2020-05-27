package com.moises.core.arch.tasking.usecase

import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import io.reactivex.Maybe
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

abstract class MaybeUseCase<Params, T>(
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) : RxUseCase<Params, Maybe<T>>() {

    fun execute(params: Params? = null): Maybe<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(jobScheduler))
            .observeOn(uiScheduler.getScheduler())
    }

    fun execute(params: Params?, observer: DisposableMaybeObserver<T>) {
        val observable = buildUseCase(params)
            .subscribeOn(Schedulers.from(jobScheduler))
            .observeOn(uiScheduler.getScheduler())
        subscribe(observable.subscribeWith(observer))
    }
}