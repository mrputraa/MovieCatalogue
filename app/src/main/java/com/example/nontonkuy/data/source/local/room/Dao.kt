package com.example.nontonkuy.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.entity.SeriesEntity

@Dao
interface Dao {
    @Query("SELECT * FROM movieentities")
    fun getUpcomingMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM seriesentities")
    fun getPopularTvShows() : LiveData<List<SeriesEntity>>

    @Query("SELECT * FROM movieentities WHERE isFavorite = 1")
    fun getFavMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM seriesentities WHERE isFavorite = 1")
    fun getFavTvShows(): LiveData<List<SeriesEntity>>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM seriesentities WHERE seriesId = :seriesId")
    fun getTvShowById(seriesId: Int): LiveData<SeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShows: List<SeriesEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(movie: SeriesEntity)






}