package com.moises.popularmovies.framework.di

import com.moises.core.di.CoreComponent
import com.moises.core.di.DatabaseModule
import com.moises.core.di.FeatureScope
import com.moises.core.di.ViewModelFactoryModule
import com.moises.popularmovies.framework.ui.PopularMoviesFragment
import dagger.Component

@Component(modules = [ViewModelFactoryModule::class,
    PopularMoviesViewModelModule::class,
    DatabaseModule::class,
    PopularMoviesModule::class], dependencies = [CoreComponent::class])
@FeatureScope
interface PopularMoviesComponent {
    fun inject(popularMoviesFragment: PopularMoviesFragment)
}