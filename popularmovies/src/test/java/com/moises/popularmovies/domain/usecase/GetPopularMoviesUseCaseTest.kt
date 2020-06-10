package com.moises.popularmovies.domain.usecase

import com.moises.core.arch.job.JobThread
import com.moises.core.arch.job.UIThread
import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.domain.repository.PopularMoviesRepository
import com.moises.popularmovies.faker.FakerClass
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetPopularMoviesUseCaseTest {

    @Mock
    private lateinit var popularMoviesRepository: PopularMoviesRepository
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private val fakerClass = FakerClass()
    private val uiScheduler: UIScheduler = UIThread()
    private val jobScheduler: JobScheduler = JobThread()
    private val page  = 1
    private val exceptionMessage = "We can not obtain popular movies"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        getPopularMoviesUseCase =
            GetPopularMoviesUseCase(popularMoviesRepository, uiScheduler, jobScheduler)
    }

    @Test
    fun `when user tries to recover popular movies always return a success response`() {
        //Setup
        whenever(popularMoviesRepository.retrieveAllPopularMovies(page)).thenReturn(Maybe.just(fakerClass.popularMovies()))
        val popularMoviesTestObserver = TestObserver<List<PopularMovie>>()
        //Act
        getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Params(page)).subscribeWith(popularMoviesTestObserver)
        popularMoviesTestObserver.awaitTerminalEvent()
        //Assert
        popularMoviesTestObserver.assertNoErrors()
        popularMoviesTestObserver.assertValue {
            it.size == 3
        }
    }

    @Test
    fun `when user tries to recover popular movies always return an error`() {
        //Setup
        whenever(popularMoviesRepository.retrieveAllPopularMovies(page))
            .thenReturn(Maybe.error(Exception(exceptionMessage)))
        val popularMoviesTestObserver = TestObserver<List<PopularMovie>>()
        //Act
        getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Params(page)).subscribeWith(popularMoviesTestObserver)
        popularMoviesTestObserver.awaitTerminalEvent()
        //Assert
        popularMoviesTestObserver.assertError {
            it.message == exceptionMessage && it is Exception
        }
    }
}