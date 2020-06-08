package com.moises.core.di

import com.moises.core.arch.job.JobThread
import com.moises.core.arch.job.UIThread
import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import com.moises.core.constants.Constants.URL_BASE
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CoreModule {

    @Provides
    fun providesHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor {
            val request = it.request()
            val builder = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type","application/json")
                .method(request.method, request.body)
            it.proceed(builder.build())
        }.build()
    }

    @Provides
    fun providesRetrofit(httpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideJobThread() : JobScheduler {
        return JobThread()
    }

    @Provides
    fun provideUIThread() : UIScheduler {
        return UIThread()
    }
}