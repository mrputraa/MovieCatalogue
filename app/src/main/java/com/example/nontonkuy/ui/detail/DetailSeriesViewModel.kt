package com.example.nontonkuy.ui.detail

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.remote.Repository

class DetailSeriesViewModel(val repo: Repository,val id: Int): ViewModel() {
    val tv = repo.getTvShowById(id)
}