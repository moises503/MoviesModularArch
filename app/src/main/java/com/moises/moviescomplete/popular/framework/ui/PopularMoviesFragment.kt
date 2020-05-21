package com.moises.moviescomplete.popular.framework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.moises.moviescomplete.R
import com.moises.moviescomplete.popular.framework.presentation.PopularMoviesViewModel

class PopularMoviesFragment : Fragment() {

    private lateinit var homeViewModel: PopularMoviesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_popular, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
