package com.example.nontonkuy.data.source.remote


import com.example.nontonkuy.api.ApiConfig


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


}