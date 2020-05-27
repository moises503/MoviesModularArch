package com.moises.popularmovies.framework.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moises.core.di.CoreInjectHelper
import com.moises.core.di.DatabaseModule
import com.moises.core.ui.ScreenState
import com.moises.popularmovies.R
import com.moises.popularmovies.domain.model.PopularMovie
import com.moises.popularmovies.framework.di.DaggerPopularMoviesComponent
import com.moises.popularmovies.framework.di.PopularMoviesModule
import com.moises.popularmovies.framework.presentation.PopularMoviesScreenState
import com.moises.popularmovies.framework.presentation.PopularMoviesViewModel
import javax.inject.Inject

open class PopularMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private lateinit var popularMoviesViewModel : PopularMoviesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onAttach(context: Context) {
        DaggerPopularMoviesComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .databaseModule(DatabaseModule(context))
            .popularMoviesModule(PopularMoviesModule(context))
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMoviesViewModel = ViewModelProvider(this, viewModelFactory).get(PopularMoviesViewModel::class.java)
        popularMoviesViewModel.retrieveAllPopularMovies()
        popularMoviesViewModel.popularMoviesState.observe(viewLifecycleOwner, Observer { screenState ->
            renderScreenState(screenState)
        })
    }

    private fun renderScreenState(screenState: ScreenState<PopularMoviesScreenState>) {
        when (screenState) {
            ScreenState.Loading -> showLoader()
            is ScreenState.Render -> renderInformation(screenState.data)
        }
    }

    private fun renderInformation(popularMoviesScreenState: PopularMoviesScreenState) {
        hideLoader()
        when(popularMoviesScreenState) {
            is PopularMoviesScreenState.Error -> showError(popularMoviesScreenState.message)
            is PopularMoviesScreenState.Movies -> showPopularMovies(popularMoviesScreenState.list)
        }
    }

    private fun showPopularMovies(movies : List<PopularMovie>) {
        Toast.makeText(requireContext(), "Movies: ${movies.size}", Toast.LENGTH_LONG).show()
        Log.e("MOVIES", "Movies: ${movies.size}")
    }

    private fun showLoader() {
        Log.e("SHOW", "show loader")
    }

    private fun hideLoader() {
        Log.e("HIDE", "hide loader")
    }

    private fun showError(message : String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        Log.e("Error", message)
    }

}
