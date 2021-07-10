package com.example.nontonkuy.ui.favorite

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.databinding.ItemsMovieBinding
import com.example.nontonkuy.ui.detail.MovieDetailActivity
import com.example.nontonkuy.utils.Constants

class FavMovieAdapter: RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>() {

    private var listFavMovies = ArrayList<MovieEntity>()

    fun setFavMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listFavMovies.clear()
        this.listFavMovies.addAll(movies)
    }

    class FavMovieViewHolder(val binding: ItemsMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemMovieTitle.text = movie.original_title
                tvItemMovieRating.text = movie.vote_average.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                    Log.d("TAG", movie.movieId.toString())
                }
                val url = "${Constants.API_POSTER_PATH}${movie.poster_path}"
                Glide.with(itemView.context)
                    .load(url)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = listFavMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listFavMovies.size
}