package com.example.nontonkuy.data.source.local

import androidx.lifecycle.LiveData
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.entity.SeriesEntity
import com.example.nontonkuy.data.source.local.room.Dao

class LocalDataSource private constructor(private val mDao: Dao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: Dao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao)
    }

    fun getUpcomingMovies(): LiveData<List<MovieEntity>> = mDao.getUpcomingMovies()
    fun getPopularTvShows(): LiveData<List<SeriesEntity>> = mDao.getPopularTvShows()
    fun getFavMovies(): LiveData<List<MovieEntity>> = mDao.getFavMovies()
    fun getFavTvShows(): LiveData<List<SeriesEntity>> = mDao.getFavTvShows()
    fun getMovieById(movieId: Int): LiveData<MovieEntity> = mDao.getMovieById(movieId)
    fun getTvShowById(seriesId: Int): LiveData<SeriesEntity> = mDao.getTvShowById(seriesId)
    fun setMovieAsFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mDao.updateMovie(movie)
    }

    fun setTvShowAsFavorite(tvShow: SeriesEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mDao.updateTvShow(tvShow)
    }

    fun insertMovies(movies: List<MovieEntity>) = mDao.insertMovie(movies)
    fun insertTvShows(tvShows: List<SeriesEntity>) = mDao.insertTvShow(tvShows)


}