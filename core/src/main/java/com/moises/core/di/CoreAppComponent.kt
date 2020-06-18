package com.moises.core.di

import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreAppComponent : CoreComponent {
    override fun getOkHttpClient(): OkHttpClient
    override fun getRetrofit(): Retrofit
    override fun getJobThread(): JobScheduler
    override fun getUIThread(): UIScheduler
}