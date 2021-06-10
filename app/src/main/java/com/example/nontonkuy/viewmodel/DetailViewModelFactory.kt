package com.example.nontonkuy.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.di.Injection
import com.example.nontonkuy.ui.detail.DetailMovieViewModel
import com.example.nontonkuy.ui.detail.DetailSeriesViewModel

class DetailViewModelFactory private constructor(val repo: Repository, val id: Int) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        fun getInstance(context: Context, id: Int): DetailViewModelFactory {
            return DetailViewModelFactory(Injection.provideRepository(context), id)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(repo, id) as T
            }

            modelClass.isAssignableFrom(DetailSeriesViewModel::class.java) -> {
                DetailSeriesViewModel(repo, id) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}