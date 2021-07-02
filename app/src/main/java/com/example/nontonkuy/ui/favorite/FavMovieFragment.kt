package com.example.nontonkuy.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.databinding.FragmentFavMovieBinding
import com.example.nontonkuy.databinding.FragmentMovieBinding
import com.example.nontonkuy.ui.movie.MovieAdapter
import com.example.nontonkuy.ui.movie.MovieViewModel
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.ViewModelFactory


class FavMovieFragment : Fragment() {
    private lateinit var fragmentFavMovieBinding: FragmentFavMovieBinding
    private lateinit var repo: Repository
    private lateinit var dataSource: RemoteDataSource

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentFavMovieBinding = FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            dataSource = RemoteDataSource()
            repo = Repository.getInstance(dataSource)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]
            val favMovieAdapter = FavMovieAdapter()

            viewModel.movies.observe(viewLifecycleOwner, {
                EspressoIdlingResource.increment()
                favMovieAdapter.setFavMovies(it)
                favMovieAdapter.notifyDataSetChanged()
                EspressoIdlingResource.decrement()
            })

            with(fragmentFavMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMovieAdapter
            }
        }

    }
}