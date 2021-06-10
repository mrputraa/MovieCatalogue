package com.example.nontonkuy.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nontonkuy.R
import com.example.nontonkuy.api.ApiConfig
import com.example.nontonkuy.api.ApiService
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.databinding.FragmentMovieBinding
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var repo: Repository
    private lateinit var dataSource: RemoteDataSource

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            dataSource = RemoteDataSource()
            repo = Repository.getInstance(dataSource)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            viewModel.movies.observe(viewLifecycleOwner, {
                EspressoIdlingResource.increment()
                movieAdapter.setMovies(it)
                movieAdapter.notifyDataSetChanged()
                EspressoIdlingResource.decrement()
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

    }
}