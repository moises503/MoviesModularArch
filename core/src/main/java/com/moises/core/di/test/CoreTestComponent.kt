package com.moises.core.di.test

import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import com.moises.core.di.CoreComponent
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreTestModule::class])
interface CoreTestComponent : CoreComponent {
    override fun getOkHttpClient(): OkHttpClient
    override fun getRetrofit(): Retrofit
    override fun getJobThread(): JobScheduler
    override fun getUIThread(): UIScheduler
}