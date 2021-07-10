package com.example.nontonkuy.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nontonkuy.R
import com.example.nontonkuy.api.ApiConfig
import com.example.nontonkuy.api.ApiService
import com.example.nontonkuy.data.source.local.LocalDataSource
import com.example.nontonkuy.data.source.local.room.NontonKuyDatabase
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.databinding.FragmentMovieBinding
import com.example.nontonkuy.utils.AppExecutors
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.ViewModelFactory
import com.example.nontonkuy.vo.Status

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var repo: Repository
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            remoteDataSource = RemoteDataSource()
            localDataSource = LocalDataSource(NontonKuyDatabase.getInstance(requireActivity()).dao())
            repo = Repository.getInstance(remoteDataSource, localDataSource)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

//            viewModel.movies.observe(viewLifecycleOwner, {
//                EspressoIdlingResource.increment()
//                movieAdapter.setMovies(it)
//                movieAdapter.notifyDataSetChanged()
//                EspressoIdlingResource.decrement()
//            })

            viewModel.movies.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            EspressoIdlingResource.increment()
                            movieAdapter.setMovies(it.data)
                            movieAdapter.notifyDataSetChanged()
                            EspressoIdlingResource.decrement()
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

    }
}