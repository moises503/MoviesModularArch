package com.moises.core.arch.tasking.dispatchers

import io.reactivex.Scheduler

interface UIScheduler {
    fun getScheduler() : Scheduler
}