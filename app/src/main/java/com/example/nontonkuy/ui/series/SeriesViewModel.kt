package com.example.nontonkuy.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nontonkuy.data.SeriesEntity
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.utils.DataDummy
import kotlinx.coroutines.launch

class SeriesViewModel(val repo: Repository): ViewModel() {
    val show = repo.getPopularTvShows()

}