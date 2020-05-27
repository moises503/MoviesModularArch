package com.moises.moviescomplete

import android.app.Application
import com.moises.core.di.CoreComponent
import com.moises.core.di.CoreComponentProvider
import com.moises.core.di.DaggerCoreComponent

class MoviesApp : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent.builder().build()
        }
        return coreComponent
    }
}