package com.example.nontonkuy.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nontonkuy.data.MovieEntity
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.utils.DataDummy
import kotlinx.coroutines.launch

class MovieViewModel(val repo: Repository): ViewModel() {
    val movies = repo.getUpcomingMovies()
}