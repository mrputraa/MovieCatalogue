package com.example.nontonkuy.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.entity.SeriesEntity
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.databinding.ItemsSeriesBinding
import com.example.nontonkuy.ui.detail.SeriesDetailActivity
import com.example.nontonkuy.utils.Constants

class FavSeriesAdapter: RecyclerView.Adapter<FavSeriesAdapter.FavSeriesViewHolder>() {

    private var listFavSeries = ArrayList<SeriesEntity>()

    fun setFavSeries(series: List<SeriesEntity>?) {
        if (series == null) return
        this.listFavSeries.clear()
        this.listFavSeries.addAll(series)
    }

    class FavSeriesViewHolder(val binding: ItemsSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesEntity) {
            with(binding) {
                tvItemSeriesTitle.text = series.original_name
                tvItemSeriesRating.text = series.vote_average.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SeriesDetailActivity::class.java)
                    intent.putExtra(SeriesDetailActivity.EXTRA_SERIES, series.seriesId)
                    itemView.context.startActivity(intent)
                }
                val url = "${Constants.API_POSTER_PATH}${series.poster_path}"
                Glide.with(itemView.context)
                    .load(url)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavSeriesViewHolder {
        val itemsSeriesBinding = ItemsSeriesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return FavSeriesViewHolder(itemsSeriesBinding)
    }

    override fun onBindViewHolder(holder: FavSeriesViewHolder, position: Int) {
        val series = listFavSeries[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = listFavSeries.size
}