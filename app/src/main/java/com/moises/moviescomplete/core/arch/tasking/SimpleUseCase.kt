package com.moises.moviescomplete.core.arch.tasking

abstract class SimpleUseCase<Params, ReturnValue> {
    abstract fun execute(params : Params?) : ReturnValue
}