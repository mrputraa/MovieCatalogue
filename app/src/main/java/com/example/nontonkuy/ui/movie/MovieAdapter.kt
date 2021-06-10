package com.example.nontonkuy.ui.movie

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.databinding.ItemsMovieBinding
import com.example.nontonkuy.ui.detail.MovieDetailActivity
import com.example.nontonkuy.utils.Constants.API_POSTER_PATH

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    class MovieViewHolder(val binding: ItemsMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                tvItemMovieTitle.text = movie.original_title
                tvItemMovieRating.text = movie.vote_average.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.id)
                    itemView.context.startActivity(intent)
                    Log.d("TAG", movie.id.toString())
                }
                val url = "${API_POSTER_PATH}${movie.poster_path}"
                Glide.with(itemView.context)
                    .load(url)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}
