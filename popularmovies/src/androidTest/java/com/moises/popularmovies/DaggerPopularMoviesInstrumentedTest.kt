package com.moises.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moises.core.di.CoreInjectHelper
import com.moises.core.di.DatabaseModule
import com.moises.popularmovies.framework.di.DaggerPopularMoviesComponent
import com.moises.popularmovies.framework.di.PopularMoviesModule
import com.moises.popularmovies.util.RecyclerViewItemCountAssertion
import com.moises.popularmovies.util.waitFor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DaggerPopularMoviesInstrumentedTest {

    @Before
    fun setup() {
        TestPopularMoviesFragmentWithDagger.popularMoviesComponent = DaggerPopularMoviesComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(ApplicationProvider.getApplicationContext()))
            .databaseModule(DatabaseModule(ApplicationProvider.getApplicationContext()))
            .popularMoviesModule(PopularMoviesModule(ApplicationProvider.getApplicationContext()))
            .build()
    }

    @Test
    fun test_whenAppIsLaunched_recyclerViewIsDisplayed() {
        launchFragmentInContainer<TestPopularMoviesFragmentWithDagger>()
        onView(ViewMatchers.withId(R.id.lst_popular_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_whenRecyclerviewIsBeingDisplayed_showAtLeast4Items() {
        launchFragmentInContainer<TestPopularMoviesFragmentWithDagger>()
        onView(isRoot()).perform(waitFor(5000))
        onView(ViewMatchers.withId(R.id.lst_popular_movies))
            .check(RecyclerViewItemCountAssertion.recyclerViewHasItems(2))
    }
}