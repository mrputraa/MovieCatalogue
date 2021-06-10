package com.example.nontonkuy.ui.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.IdlingResource
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.databinding.FragmentSeriesBinding
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.ViewModelFactory

class SeriesFragment : Fragment() {

    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding
    private lateinit var repo: Repository
    private lateinit var dataSource: RemoteDataSource

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSeriesBinding = FragmentSeriesBinding.inflate(inflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            dataSource = RemoteDataSource()
            repo = Repository.getInstance(dataSource)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]
            val seriesAdapter = SeriesAdapter()

            viewModel.show.observe(viewLifecycleOwner, {
                EspressoIdlingResource.increment()
                seriesAdapter.setSeries(it)
                seriesAdapter.notifyDataSetChanged()
                EspressoIdlingResource.decrement()
            })

            with(fragmentSeriesBinding.rvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
        }
    }
}