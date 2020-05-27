package com.moises.core.arch.tasking.usecase

abstract class CoroutineUseCase <Params, ReturnValue> {
    abstract suspend fun execute(params : Params?) : ReturnValue
}