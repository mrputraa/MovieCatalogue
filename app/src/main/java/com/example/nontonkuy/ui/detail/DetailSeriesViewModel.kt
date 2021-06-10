package com.example.nontonkuy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nontonkuy.data.SeriesEntity
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.utils.DataDummy
import kotlinx.coroutines.launch

class DetailSeriesViewModel(val repo: Repository,val id: Int): ViewModel() {
    val tv = repo.getTvShowById(id)

//    private val _tv: MutableLiveData<Series> = MutableLiveData()
//    val tv : LiveData<Series> = _tv
//
//    fun getTvShowById(id: Int)  {
//        viewModelScope.launch {
//            _tv.value = repo.getTvShowById(id)
//        }
//    }
}