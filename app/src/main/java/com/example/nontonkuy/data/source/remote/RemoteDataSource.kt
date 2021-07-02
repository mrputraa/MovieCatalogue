package com.example.nontonkuy.data.source.remote


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nontonkuy.api.ApiConfig
import com.example.nontonkuy.data.source.remote.ApiResponse.*


class RemoteDataSource () {

    companion object {
        const val PAGE = 1

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        return ApiConfig.getApiService().getUpcomingMovies(PAGE).results
    }

    suspend fun getPopularTvShows(): List<Series> {
        return ApiConfig.getApiService().getPopularTvShows(PAGE).results
    }

    suspend fun getMovieById(id: Int): Movie {
        return ApiConfig.getApiService().getMovieById(id)
    }

    suspend fun getTvShowById(id: Int): Series {
        return ApiConfig.getApiService().getTvShowById(id)
    }

//    suspend fun getUpcomingMovies(): LiveData<ApiResponse<List<Movie>>> {
//        val res = MutableLiveData<ApiResponse<List<Movie>>>()
//        res.value = ApiResponse.success(ApiConfig.getApiService().getUpcomingMovies(PAGE).results)
//        return res
//    }
//
//    suspend fun getPopularTvShows(): LiveData<ApiResponse<List<Series>>> {
//        val res = MutableLiveData<ApiResponse<List<Series>>>()
//        res.value = ApiResponse.success(ApiConfig.getApiService().getPopularTvShows(PAGE).results)
//        return res
//    }
//
//    suspend fun getMovieById(id: Int): LiveData<ApiResponse<Movie>> {
//        val res = MutableLiveData<ApiResponse<Movie>>()
//        res.value = ApiResponse.success(ApiConfig.getApiService().getMovieById(id))
//        return res
//    }
//
//    suspend fun getTvShowById(id: Int): LiveData<ApiResponse<Series>> {
//        val res = MutableLiveData<ApiResponse<Series>>()
//        res.value = ApiResponse.success(ApiConfig.getApiService().getTvShowById(id))
//        return res
//    }





}