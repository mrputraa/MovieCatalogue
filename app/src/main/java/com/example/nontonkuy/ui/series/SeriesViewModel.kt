package com.example.nontonkuy.ui.series

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.remote.Repository

class SeriesViewModel(val repo: Repository): ViewModel() {
    val show = repo.getPopularTvShows()

}