package com.moises.popularmovies

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moises.core.arch.job.JobThread
import com.moises.core.arch.job.UIThread
import com.moises.core.arch.tasking.dispatchers.JobScheduler
import com.moises.core.arch.tasking.dispatchers.UIScheduler
import com.moises.popularmovies.domain.repository.PopularMoviesRepository
import com.moises.popularmovies.domain.usecase.GetPopularMoviesUseCase
import com.moises.popularmovies.framework.presentation.PopularMoviesViewModel
import com.moises.popularmovies.framework.resources.PopularMoviesResources
import com.moises.popularmovies.util.FakerClass
import com.moises.popularmovies.util.RecyclerViewItemCountAssertion.Companion.recyclerViewHasItems
import com.moises.popularmovies.util.TestUtil.atPosition
import com.moises.popularmovies.util.TestUtil.withRecyclerView
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PopularMoviesInstrumentedTest {

    @Mock
    private lateinit var popularMoviesResources: PopularMoviesResources
    @Mock
    private lateinit var popularMoviesRepository: PopularMoviesRepository
    private lateinit var popularMoviesUseCase: GetPopularMoviesUseCase
    private val uiScheduler: UIScheduler = UIThread()
    private val jobScheduler: JobScheduler = JobThread()
    private val popularMoviesResourceError = "We can not obtain current popular movies"
    private val page = 1
    private val fakerClass = FakerClass()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        popularMoviesUseCase =
            GetPopularMoviesUseCase(popularMoviesRepository, uiScheduler, jobScheduler)
        whenever(popularMoviesResources.popularMoviesErrorMessage()).thenReturn(
            popularMoviesResourceError
        )
        whenever(popularMoviesRepository.retrieveAllPopularMovies(1)).thenReturn(
            Maybe.just(
                fakerClass.popularMovies()
            )
        )
        TestPopularMoviesFragmentWithoutDagger.popularMoviesViewModelTest =
            PopularMoviesViewModel(popularMoviesUseCase, popularMoviesResources)
    }

    @Test
    fun test_whenAppIsLaunched_recyclerviewIsDisplayed() {
        launchFragmentInContainer<TestPopularMoviesFragmentWithoutDagger>()
        onView(withId(R.id.lst_popular_movies)).check(matches(isDisplayed()))
    }

    @Test
    fun test_whenRecyclerviewIsBeingDisplayed_showThreeItems() {
        launchFragmentInContainer<TestPopularMoviesFragmentWithoutDagger>()
        onView(withId(R.id.lst_popular_movies)).check(recyclerViewHasItems(3))
    }

    @Test
    fun test_whenRecyclerViewPopulatesAdapter_inFirstPosition_weCanSeeUIElements() {
        launchFragmentInContainer<TestPopularMoviesFragmentWithoutDagger>()
        onView(withId(R.id.lst_popular_movies))
            .check(matches(atPosition(0, withId(R.id.cvMovie))))
        onView(withRecyclerView(R.id.lst_popular_movies)
            .atPositionOnView(0, R.id.tvMovieTitle))
            .check(matches(withText("title 1")));
    }
}
