package com.example.nontonkuy.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.remote.Repository

class FavSeriesViewModel(val repo: Repository): ViewModel() {
    val show = repo.getFavTvShows()
}