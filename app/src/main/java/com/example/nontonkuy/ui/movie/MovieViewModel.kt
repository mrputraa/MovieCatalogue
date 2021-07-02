package com.example.nontonkuy.ui.movie

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.remote.Repository

class MovieViewModel(val repo: Repository): ViewModel() {
    val movies = repo.getUpcomingMovies()
}