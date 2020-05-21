package com.moises.moviescomplete.core.arch.tasking

abstract class CoroutineUseCase <Params, ReturnValue> {
    abstract suspend fun execute(params : Params?) : ReturnValue
}