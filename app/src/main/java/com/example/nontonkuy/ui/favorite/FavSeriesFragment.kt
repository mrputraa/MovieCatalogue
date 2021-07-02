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
import com.example.nontonkuy.databinding.FragmentFavSeriesBinding
import com.example.nontonkuy.databinding.FragmentSeriesBinding
import com.example.nontonkuy.ui.series.SeriesAdapter
import com.example.nontonkuy.ui.series.SeriesViewModel
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.ViewModelFactory


class FavSeriesFragment : Fragment() {

    private lateinit var fragmentFavSeriesFragment: FragmentFavSeriesBinding
    private lateinit var repo: Repository
    private lateinit var dataSource: RemoteDataSource

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentFavSeriesFragment = FragmentFavSeriesBinding.inflate(inflater, container, false)
        return fragmentFavSeriesFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            dataSource = RemoteDataSource()
            repo = Repository.getInstance(dataSource)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavSeriesViewModel::class.java]
            val favSeriesAdapter = FavSeriesAdapter()

            viewModel.show.observe(viewLifecycleOwner, {
                EspressoIdlingResource.increment()
                favSeriesAdapter.setFavSeries(it)
                favSeriesAdapter.notifyDataSetChanged()
                EspressoIdlingResource.decrement()
            })

            with(fragmentFavSeriesFragment.rvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favSeriesAdapter
            }
        }
    }


}