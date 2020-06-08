package com.moises.popularmovies.framework.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moises.core.di.CoreInjectHelper
import com.moises.core.di.DatabaseModule
import com.moises.core.ui.*
import com.moises.popularmovies.R
import com.moises.popularmovies.databinding.FragmentPopularBinding
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.framework.di.DaggerPopularMoviesComponent
import com.moises.popularmovies.framework.di.PopularMoviesModule
import com.moises.popularmovies.framework.presentation.PopularMoviesScreenState
import com.moises.popularmovies.framework.presentation.PopularMoviesViewModel
import javax.inject.Inject

open class PopularMoviesFragment : BaseFragment<ScreenState<PopularMoviesScreenState>>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel
    private lateinit var fragmentPopularBinding: FragmentPopularBinding
    private lateinit var popularMoviesDataBindingAdapter: GenericDataBindingAdapter<PopularMovie>


    override fun onAttach(context: Context) {
        setupInjection(context)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    override fun setupInjection(context: Context) {
        DaggerPopularMoviesComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .databaseModule(DatabaseModule(context))
            .popularMoviesModule(PopularMoviesModule(context))
            .build()
            .inject(this)
    }

    override fun bindFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        fragmentPopularBinding = FragmentPopularBinding.inflate(inflater, container, false)
        return fragmentPopularBinding.root
    }

    override fun attachObservers() {
        popularMoviesViewModel =
            ViewModelProvider(this, viewModelFactory).get(PopularMoviesViewModel::class.java)
        popularMoviesViewModel.retrieveAllPopularMovies()
        popularMoviesViewModel.popularMoviesState.observe(
            viewLifecycleOwner,
            Observer { screenState ->
                renderScreenState(screenState)
            })
    }


    override fun bindViews() {
        popularMoviesDataBindingAdapter =
            GenericDataBindingAdapter(BR.movie, R.layout.item_popular_movie)
        fragmentPopularBinding.lstPopularMovies.apply {
            addItemDecoration(SpacesItemDecoration(SPACE_ITEM_DECORATION))
            adapter = popularMoviesDataBindingAdapter
            layoutManager = adapterLayoutManager
        }
    }

    override fun renderScreenState(screenState: ScreenState<PopularMoviesScreenState>) {
        when (screenState) {
            ScreenState.Loading -> showLoader()
            is ScreenState.Render -> renderInformation(screenState.data)
        }
    }

    private fun renderInformation(screenStateInformation: PopularMoviesScreenState) {
        hideLoader()
        when (screenStateInformation) {
            is PopularMoviesScreenState.Error -> showError(screenStateInformation.message)
            is PopularMoviesScreenState.Movies -> showPopularMovies(screenStateInformation.list)
        }
    }

    private fun showPopularMovies(movies: List<PopularMovie>) {
        popularMoviesDataBindingAdapter.setItems(movies.toMutableList())
    }

    private fun showLoader() {
        fragmentPopularBinding.pbPopularMovies.show()
    }

    private fun hideLoader() {
        fragmentPopularBinding.pbPopularMovies.hide()
    }

    private fun showError(message: String) {
        requireContext().showLargeToast(message)
    }

}
