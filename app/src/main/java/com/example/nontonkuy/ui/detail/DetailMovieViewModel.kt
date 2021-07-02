package com.example.nontonkuy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.Repository


class DetailMovieViewModel(val repo: Repository, val id: Int): ViewModel() {
    val movie = repo.getMovieById(id)


    fun setMovieAsFavorite() {
//        repo.setMovieAsFavorite()
    }
}