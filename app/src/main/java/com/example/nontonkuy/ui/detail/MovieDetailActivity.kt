package com.example.nontonkuy.ui.detail

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
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.room.NontonKuyDatabase
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.databinding.ActivityMovieDetailBinding
import com.example.nontonkuy.databinding.ContentDetailMovieBinding
import com.example.nontonkuy.utils.AppExecutors
import com.example.nontonkuy.utils.Constants.API_POSTER_PATH
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.DetailViewModelFactory
import com.example.nontonkuy.vo.Status

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailMovieBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var repo: Repository
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMovieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        detailMovieBinding = activityMovieDetailBinding.detailMovieContent
        setContentView(activityMovieDetailBinding.root)

        setSupportActionBar(activityMovieDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        remoteDataSource = RemoteDataSource()
        localDataSource = LocalDataSource(NontonKuyDatabase.getInstance(this).dao())
        repo = Repository.getInstance(remoteDataSource, localDataSource)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            val factory = DetailViewModelFactory.getInstance(this, movieId)
            viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

//            viewModel.movie.observe(this, { movie ->
//                EspressoIdlingResource.increment()
//                populateMovie(movie)
//                EspressoIdlingResource.decrement()})

            viewModel.movie.observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            EspressoIdlingResource.increment()
                            populateMovie(movie.data)
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
    private fun populateMovie(movie: MovieEntity?) {
        detailMovieBinding.tvMovieTitle.text = movie?.original_title
        detailMovieBinding.tvMovieRating.text = movie?.vote_average.toString()
        detailMovieBinding.tvMovieYear.text = movie?.release_date
        detailMovieBinding.tvMovieDescription.text = movie?.overview
        val url = "${API_POSTER_PATH}${movie?.poster_path}"
        Glide.with(this)
            .load(url)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailMovieBinding.imagePoster)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.movie.observe(this, {
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
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_true)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_false)
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