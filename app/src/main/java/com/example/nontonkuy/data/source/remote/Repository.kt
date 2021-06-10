package com.example.nontonkuy.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class Repository private constructor(private val remoteDataSource: RemoteDataSource){

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }
    }

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

//    suspend fun getTvShowById(id: Int): Series {
//        return remoteDataSource.getTvShowById(id)
//    }

//    suspend fun getMovieById(id: Int): Movie {
//        return remoteDataSource.getMovieById(id)
//    }
}