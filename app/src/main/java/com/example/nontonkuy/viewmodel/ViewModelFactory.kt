package com.example.nontonkuy.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.di.Injection
import com.example.nontonkuy.ui.detail.DetailMovieViewModel
import com.example.nontonkuy.ui.detail.DetailSeriesViewModel
import com.example.nontonkuy.ui.movie.MovieViewModel
import com.example.nontonkuy.ui.series.SeriesViewModel

class ViewModelFactory private constructor(val repo: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repo) as T
            }
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> {
                SeriesViewModel(repo) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}