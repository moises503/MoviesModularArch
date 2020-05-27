package com.moises.popularmovies.framework.di

import androidx.lifecycle.ViewModel
import com.moises.core.di.ViewModelKey
import com.moises.popularmovies.framework.presentation.PopularMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PopularMoviesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    abstract fun bindPopularMoviesViewModel(popularMoviesViewModel: PopularMoviesViewModel): ViewModel
}