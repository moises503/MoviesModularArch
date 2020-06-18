package com.moises.popularmovies.di

import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreTestModule::class])
interface CoreTestComponent {
    fun getOkHttpClient() : OkHttpClient
    fun getRetrofit() : Retrofit
    fun getJobThread() : JobScheduler
    fun getUIThread() : UIScheduler
}