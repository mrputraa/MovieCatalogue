package com.example.nontonkuy.ui.series

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.databinding.ItemsSeriesBinding
import com.example.nontonkuy.ui.detail.SeriesDetailActivity
import com.example.nontonkuy.utils.Constants.API_POSTER_PATH

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private var listSeries = ArrayList<Series>()

    fun setSeries(series: List<Series>?) {
        if (series == null) return
        this.listSeries.clear()
        this.listSeries.addAll(series)
    }

    class SeriesViewHolder(val binding: ItemsSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: Series) {
            with(binding) {
                tvItemSeriesTitle.text = series.original_name
                tvItemSeriesRating.text = series.vote_average.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SeriesDetailActivity::class.java)
                    intent.putExtra(SeriesDetailActivity.EXTRA_SERIES, series.id)
                    itemView.context.startActivity(intent)
                }
                val url = "${API_POSTER_PATH}${series.poster_path}"
                Glide.with(itemView.context)
                    .load(url)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val itemsSeriesBinding = ItemsSeriesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(itemsSeriesBinding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = listSeries[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = listSeries.size
}