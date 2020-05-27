package com.moises.core.arch.tasking.usecase

abstract class SimpleUseCase<Params, ReturnValue> {
    abstract fun execute(params : Params?) : ReturnValue
}