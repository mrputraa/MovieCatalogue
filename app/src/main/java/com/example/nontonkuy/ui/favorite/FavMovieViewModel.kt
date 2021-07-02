package com.example.nontonkuy.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.remote.Repository

class FavMovieViewModel(val repo: Repository): ViewModel() {
    val movies = repo.getFavMovies()
}