package com.example.nontonkuy.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nontonkuy.data.MovieEntity
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.utils.DataDummy
import kotlinx.coroutines.launch

class DetailMovieViewModel(val repo: Repository, val id: Int): ViewModel() {
    val movie = repo.getMovieById(id)
}
