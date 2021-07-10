package com.example.nontonkuy.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.LocalDataSource
import com.example.nontonkuy.data.source.local.entity.SeriesEntity
import com.example.nontonkuy.data.source.local.room.NontonKuyDatabase
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.databinding.ActivitySeriesDetailBinding
import com.example.nontonkuy.databinding.ContentDetailSeriesBinding
import com.example.nontonkuy.ui.favorite.FavoriteActivity
import com.example.nontonkuy.utils.AppExecutors
import com.example.nontonkuy.utils.Constants
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.DetailViewModelFactory
import com.example.nontonkuy.vo.Status

class SeriesDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var detailSeriesBinding: ContentDetailSeriesBinding
    private lateinit var viewModel: DetailSeriesViewModel
    private lateinit var repo: Repository
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitySeriesDetailBinding = ActivitySeriesDetailBinding.inflate(layoutInflater)
        detailSeriesBinding = activitySeriesDetailBinding.detailSeriesContent
        setContentView(activitySeriesDetailBinding.root)

        setSupportActionBar(activitySeriesDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        remoteDataSource = RemoteDataSource()
        localDataSource = LocalDataSource(NontonKuyDatabase.getInstance(this).dao())
        repo = Repository.getInstance(remoteDataSource, localDataSource)

        val extras = intent.extras

        if (extras != null) {
            val seriesId = extras.getInt(EXTRA_SERIES)
            val factory = DetailViewModelFactory.getInstance(this, seriesId)
            viewModel = ViewModelProvider(this, factory)[DetailSeriesViewModel::class.java]
//            viewModel.tv.observe(this, { series ->
//                EspressoIdlingResource.increment()
//                populateSeries(series)
//                EspressoIdlingResource.decrement()})

            viewModel.tv.observe(this, { series ->
                if (series != null) {
                    when (series.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            EspressoIdlingResource.increment()
                            populateSeries(series.data)
                            EspressoIdlingResource.decrement()
                        }
                        Status.ERROR -> {
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

        }
    }

    private fun populateSeries(series: SeriesEntity?) {
        detailSeriesBinding.tvSeriesTitle.text = series?.original_name
        detailSeriesBinding.tvSeriesRating.text = series?.vote_average.toString()
        detailSeriesBinding.tvSeriesEpisodes.text = series?.number_of_episodes.toString()

        detailSeriesBinding.tvSeriesYear.text = series?.first_air_date
        detailSeriesBinding.tvSeriesDescription.text = series?.overview
        val url = "${Constants.API_POSTER_PATH}${series?.poster_path}"
        Glide.with(this)
            .load(url)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailSeriesBinding.imagePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.tv.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> if (it.data != null) {
                        val state = it.data.isFavorite
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon =ContextCompat.getDrawable(this, R.drawable.ic_favorite_true)
        } else {
            menuItem?.icon =ContextCompat.getDrawable(this, R.drawable.ic_favorite_false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}