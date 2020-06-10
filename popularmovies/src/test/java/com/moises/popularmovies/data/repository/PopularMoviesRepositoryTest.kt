package com.moises.popularmovies.data.repository

import com.moises.popularmovies.data.datasource.local.PopularMoviesLocalDataSource
import com.moises.popularmovies.data.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.popularmovies.domain.repository.PopularMoviesRepository
import com.moises.popularmovies.faker.FakerClass
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class PopularMoviesRepositoryTest {
    @Mock
    private lateinit var popularMoviesLocalDataSource: PopularMoviesLocalDataSource
    @Mock
    private lateinit var popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
    private lateinit var popularMoviesRepository: PopularMoviesRepository
    private val fakerClass = FakerClass()
    private val page = 1
    private val exceptionMessage = "We can not obtain popular movies"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        popularMoviesRepository =
            PopularMoviesRepositoryImpl(popularMoviesLocalDataSource, popularMoviesRemoteDataSource)
    }

    @Test
    fun `get popular movies from remote and local data source successfully`() {
        //Setup
        whenever(popularMoviesLocalDataSource.retrieveAllPopularMoviesFromDatabaseBy(page))
            .thenReturn(Single.just(fakerClass.popularMovies()))
        whenever(popularMoviesRemoteDataSource.retrieveAllPopularMoviesFromServer(page)).thenReturn(
            Single.just(fakerClass.popularMovies())
        )
        whenever(popularMoviesLocalDataSource.saveAPopularMovie(any())).thenReturn(Completable.complete())
        //Act
        //Assert
        popularMoviesRepository.retrieveAllPopularMovies(page).test().assertNoErrors().assertValue {
            it.size == 3
        }
    }

    @Test
    fun `get popular movies from remote and local data source unsuccessfully`() {
        //Setup
        whenever(popularMoviesLocalDataSource.retrieveAllPopularMoviesFromDatabaseBy(page)).thenReturn(
            Single.error(Exception(exceptionMessage))
        )
        whenever(popularMoviesRemoteDataSource.retrieveAllPopularMoviesFromServer(page)).thenReturn(
            Single.error(Exception(exceptionMessage))
        )
        //Act
        //Assert
        popularMoviesRepository.retrieveAllPopularMovies(page).test().assertError {
            it.message == exceptionMessage && it is Exception
        }
    }

    @Test
    fun `get popular movies only from local data source successfully`() {
        //Setup
        whenever(popularMoviesLocalDataSource.retrieveAllPopularMoviesFromDatabaseBy(page)).thenReturn(
            Single.just(fakerClass.popularMovies()))
        whenever(popularMoviesRemoteDataSource.retrieveAllPopularMoviesFromServer(page)).thenReturn(
            Single.just(emptyList()))
        whenever(popularMoviesLocalDataSource.saveAPopularMovie(any())).thenReturn(Completable.complete())
        //Act
        //Assert
        popularMoviesRepository.retrieveAllPopularMovies(page).test().assertNoErrors().assertValue {
            it.size == 3
        }
    }

    @Test
    fun `get popular movies only from remote data source successfully`() {
        //Setup
        whenever(popularMoviesLocalDataSource.retrieveAllPopularMoviesFromDatabaseBy(page)).thenReturn(
            Single.just(emptyList()))
        whenever(popularMoviesRemoteDataSource.retrieveAllPopularMoviesFromServer(page)).thenReturn(
            Single.just(fakerClass.popularMovies()))
        whenever(popularMoviesLocalDataSource.saveAPopularMovie(any())).thenReturn(Completable.complete())
        //Act
        //Assert
        popularMoviesRepository.retrieveAllPopularMovies(page).test().assertNoErrors().assertValue {
            it.size == 3
        }
    }
}