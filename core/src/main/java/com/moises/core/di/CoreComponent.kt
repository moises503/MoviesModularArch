package com.moises.core.di

import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import com.moises.core.database.MoviesDatabase
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

interface CoreComponent {
    fun getOkHttpClient() : OkHttpClient
    fun getRetrofit() : Retrofit
    fun getJobThread() : JobScheduler
    fun getUIThread() : UIScheduler
}