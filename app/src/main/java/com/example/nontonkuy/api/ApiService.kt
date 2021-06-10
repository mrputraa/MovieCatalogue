package com.example.nontonkuy.api

import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.MovieResponse
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.data.source.remote.SeriesResponse
import com.example.nontonkuy.utils.Constants.API_KEY
import retrofit2.http.*

interface ApiService {

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(
        @Query("page") position: Int
    ): MovieResponse

    @GET("tv/popular?api_key=$API_KEY")
    suspend fun getPopularTvShows(
        @Query("page") position: Int
    ): SeriesResponse

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun getMovieById(
        @Path("id") movieId: Int
    ): Movie

    @GET("tv/{tv_id}?api_key=$API_KEY")
    suspend fun getTvShowById(
        @Path("tv_id") tvId: Int
    ): Series


}