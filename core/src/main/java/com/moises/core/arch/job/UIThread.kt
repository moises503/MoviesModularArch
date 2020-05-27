package com.moises.core.arch.job

import com.moises.core.arch.tasking.dispatchers.UIScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


class UIThread : UIScheduler {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}