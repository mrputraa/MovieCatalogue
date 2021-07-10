package com.example.nontonkuy.ui.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nontonkuy.data.source.local.LocalDataSource
import com.example.nontonkuy.data.source.local.room.NontonKuyDatabase
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.databinding.FragmentSeriesBinding
import com.example.nontonkuy.utils.AppExecutors
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.ViewModelFactory
import com.example.nontonkuy.vo.Status

class SeriesFragment : Fragment() {

    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding
    private lateinit var repo: Repository
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var appExecutors: AppExecutors

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSeriesBinding = FragmentSeriesBinding.inflate(inflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            remoteDataSource = RemoteDataSource()
            localDataSource = LocalDataSource(NontonKuyDatabase.getInstance(requireActivity()).dao())
            repo = Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]
            val seriesAdapter = SeriesAdapter()

//            viewModel.show.observe(viewLifecycleOwner, {
//                EspressoIdlingResource.increment()
//                seriesAdapter.setSeries(it)
//                seriesAdapter.notifyDataSetChanged()
//                EspressoIdlingResource.decrement()
//            })

            viewModel.show.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            EspressoIdlingResource.increment()
                            seriesAdapter.setSeries(it.data)
                            seriesAdapter.notifyDataSetChanged()
                            EspressoIdlingResource.decrement()
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

            with(fragmentSeriesBinding.rvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
        }
    }
}