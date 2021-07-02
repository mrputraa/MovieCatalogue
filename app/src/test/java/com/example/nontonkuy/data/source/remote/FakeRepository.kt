package com.example.nontonkuy.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class FakeRepository (private val remoteDataSource: RemoteDataSource){

    fun getUpcomingMovies(): LiveData<List<Movie>> {
        return liveData {
            val movies = remoteDataSource.getUpcomingMovies()
            emit(movies)
        }
    }

    fun getPopularTvShows(): LiveData<List<Series>> {
        return liveData {
            val series = remoteDataSource.getPopularTvShows()
            emit(series)
        }
    }


    fun getMovieById(id: Int): LiveData<Movie> {
        return liveData {
            val movie = remoteDataSource.getMovieById(id)
            emit(movie)
        }
    }

    fun getTvShowById(id: Int): LiveData<Series> {
        return liveData {
            val series = remoteDataSource.getTvShowById(id)
            emit(series)
        }
    }
}