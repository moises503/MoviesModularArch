package com.moises.core.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class BaseFragment<ScreenState> : Fragment() {

    abstract fun setupInjection(context: Context)
    abstract fun bindViews()
    abstract fun bindFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View
    abstract fun attachObservers()
    abstract fun renderScreenState(screenState : ScreenState)

    open fun hideLoader() = Unit
    open fun showLoader()  = Unit
    open fun showError(message : String) = Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindFragmentView(inflater, container)
    }

    //Verify if the screen orientation is landscape or portrait
    private val isLandScape by lazy {
        requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    /**
     * Method to get recyclerview's columns according to the current orientation
     */
    protected fun getColumnsByOrientation(isLandScape: Boolean) =
        if (isLandScape) RECYCLER_VIEW_SPAN_COUNT_LANDSCAPE
        else RECYCLER_VIEW_SPAN_COUNT_PORTRAIT

    protected val adapterLayoutManager by lazy {
        StaggeredGridLayoutManager(
            getColumnsByOrientation(isLandScape),
            StaggeredGridLayoutManager.VERTICAL)
    }

    companion object {
        const val RECYCLER_VIEW_SPAN_COUNT_PORTRAIT = 2
        const val RECYCLER_VIEW_SPAN_COUNT_LANDSCAPE = 3
        const val SPACE_ITEM_DECORATION = 12
    }
}