package com.example.nontonkuy.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nontonkuy.R
import com.example.nontonkuy.data.SeriesEntity
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.databinding.ActivitySeriesDetailBinding
import com.example.nontonkuy.databinding.ContentDetailSeriesBinding
import com.example.nontonkuy.utils.Constants
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.DetailViewModelFactory
import com.example.nontonkuy.viewmodel.ViewModelFactory

class SeriesDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var detailSeriesBinding: ContentDetailSeriesBinding
    private lateinit var repo: Repository
    private lateinit var dataSource: RemoteDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitySeriesDetailBinding = ActivitySeriesDetailBinding.inflate(layoutInflater)
        detailSeriesBinding = activitySeriesDetailBinding.detailSeriesContent
        setContentView(activitySeriesDetailBinding.root)

        setSupportActionBar(activitySeriesDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        dataSource = RemoteDataSource()
        repo = Repository.getInstance(dataSource)
        val extras = intent.extras

        if (extras != null) {
            val seriesId = extras.getInt(EXTRA_SERIES)
            val factory = DetailViewModelFactory.getInstance(this, seriesId)
            val viewModel = ViewModelProvider(this, factory)[DetailSeriesViewModel::class.java]
            viewModel.tv.observe(this, { series -> 
                EspressoIdlingResource.increment()
                populateSeries(series)
                EspressoIdlingResource.decrement()})
        }
    }

    private fun populateSeries(series: Series) {
        detailSeriesBinding.tvSeriesTitle.text = series.original_name
        detailSeriesBinding.tvSeriesRating.text = series.vote_average.toString()
        detailSeriesBinding.tvSeriesEpisodes.text = series.number_of_episodes.toString()

        detailSeriesBinding.tvSeriesYear.text = series.first_air_date
        detailSeriesBinding.tvSeriesDescription.text = series.overview
        val url = "${Constants.API_POSTER_PATH}${series.poster_path}"
        Glide.with(this)
            .load(url)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailSeriesBinding.imagePoster)
    }
}