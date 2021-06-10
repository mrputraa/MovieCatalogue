package com.example.nontonkuy.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nontonkuy.R
import com.example.nontonkuy.data.MovieEntity
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.databinding.ActivityMovieDetailBinding
import com.example.nontonkuy.databinding.ContentDetailMovieBinding
import com.example.nontonkuy.ui.movie.MovieViewModel
import com.example.nontonkuy.utils.Constants.API_POSTER_PATH
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.viewmodel.DetailViewModelFactory
import com.example.nontonkuy.viewmodel.ViewModelFactory

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailMovieBinding: ContentDetailMovieBinding
    private lateinit var repo: Repository
    private lateinit var dataSource: RemoteDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMovieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        detailMovieBinding = activityMovieDetailBinding.detailMovieContent
        setContentView(activityMovieDetailBinding.root)

        setSupportActionBar(activityMovieDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dataSource = RemoteDataSource()
        repo = Repository.getInstance(dataSource)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            val factory = DetailViewModelFactory.getInstance(this, movieId)
            val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
            viewModel.movie.observe(this, { movie ->
                EspressoIdlingResource.increment()
                populateMovie(movie)
                EspressoIdlingResource.decrement()})
        }
    }
    private fun populateMovie(movie: Movie) {
        detailMovieBinding.tvMovieTitle.text = movie.original_title
        detailMovieBinding.tvMovieRating.text = movie.vote_average.toString()
        detailMovieBinding.tvMovieYear.text = movie.release_date
        detailMovieBinding.tvMovieDescription.text = movie.overview
        val url = "${API_POSTER_PATH}${movie.poster_path}"
        Glide.with(this)
            .load(url)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailMovieBinding.imagePoster)

    }

}