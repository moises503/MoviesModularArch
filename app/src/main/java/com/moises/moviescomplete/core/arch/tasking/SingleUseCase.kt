package com.moises.moviescomplete.core.arch.tasking

import com.moises.moviescomplete.core.arch.tasking.dispatchers.JobScheduler
import com.moises.moviescomplete.core.arch.tasking.dispatchers.UIScheduler
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Params, T>(
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) : RxUseCase<Params, Single<T>>() {

    fun execute(params: Params? = null): Single<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(jobScheduler))
            .observeOn(uiScheduler.getScheduler())
    }

    fun execute(params: Params?, observer: DisposableSingleObserver<T>) {
        val observable = buildUseCase(params)
            .subscribeOn(Schedulers.from(jobScheduler))
            .observeOn(uiScheduler.getScheduler())
        subscribe(observable.subscribeWith(observer))
    }
}