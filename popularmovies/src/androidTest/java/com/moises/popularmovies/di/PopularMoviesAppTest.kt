package com.moises.popularmovies.di

import android.app.Application
import com.moises.core.di.CoreComponent
import com.moises.core.di.CoreComponentProvider
import com.moises.core.di.DaggerCoreAppComponent

class PopularMoviesAppTest : Application() , CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreAppComponent.builder().build()
        }
        return coreComponent
    }
}