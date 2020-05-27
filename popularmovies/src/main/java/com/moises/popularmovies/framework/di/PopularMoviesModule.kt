package com.moises.popularmovies.framework.di

import android.content.Context
import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import com.moises.core.database.dao.PopularMoviesDao
import com.moises.popularmovies.data.datasource.local.PopularMoviesLocalDataSource
import com.moises.popularmovies.data.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.popularmovies.data.repository.PopularMoviesRepositoryImpl
import com.moises.popularmovies.domain.repository.PopularMoviesRepository
import com.moises.popularmovies.domain.usecase.GetPopularMoviesUseCase
import com.moises.popularmovies.framework.datasource.endpoint.PopularMoviesEndPoint
import com.moises.popularmovies.framework.datasource.local.PopularMoviesLocalDataSourceImpl
import com.moises.popularmovies.framework.datasource.remote.PopularMoviesRemoteDataSourceImpl
import com.moises.popularmovies.framework.resources.PopularMoviesResources
import com.moises.popularmovies.framework.resources.PopularMoviesResourcesImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PopularMoviesModule(private val context: Context) {

    @Provides
    fun providesPopularMoviesEndPoint(retrofit: Retrofit): PopularMoviesEndPoint {
        return retrofit.create(PopularMoviesEndPoint::class.java)
    }

    @Provides
    fun providesPopularMoviesLocalDataSource(popularMoviesDao: PopularMoviesDao): PopularMoviesLocalDataSource {
        return PopularMoviesLocalDataSourceImpl(popularMoviesDao)
    }

    @Provides
    fun providesPopularMoviesRemoteDataSource(popularMoviesEndPoint: PopularMoviesEndPoint): PopularMoviesRemoteDataSource {
        return PopularMoviesRemoteDataSourceImpl(popularMoviesEndPoint)
    }

    @Provides
    fun providesPopularMoviesRepository(
        popularMoviesLocalDataSource: PopularMoviesLocalDataSource,
        popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
    ): PopularMoviesRepository {
        return PopularMoviesRepositoryImpl(
            popularMoviesLocalDataSource,
            popularMoviesRemoteDataSource
        )
    }

    @Provides
    fun providesGetPopularMoviesUseCase(
        popularMoviesRepository: PopularMoviesRepository,
        uiScheduler: UIScheduler,
        jobScheduler: JobScheduler
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(popularMoviesRepository, uiScheduler, jobScheduler)
    }

    @Provides
    fun providesPopularMoviesResources() : PopularMoviesResources {
        return PopularMoviesResourcesImpl(context)
    }
}