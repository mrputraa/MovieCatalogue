package com.example.nontonkuy.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.nontonkuy.data.NetworkBoundResource
import com.example.nontonkuy.data.source.local.LocalDataSource
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.entity.SeriesEntity
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.utils.AppExecutors
import com.example.nontonkuy.vo.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource){

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localData).apply { instance = this }
            }
    }

//    fun getUpcomingMovies(): LiveData<List<Movie>> {
//        return liveData {
//            EspressoIdlingResource.increment()
//            val movies = remoteDataSource.getUpcomingMovies()
//            emit(movies)
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    fun getPopularTvShows(): LiveData<List<Series>> {
//        EspressoIdlingResource.increment()
//
//        return liveData {
//            val series = remoteDataSource.getPopularTvShows()
//            emit(series)
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    fun getMovieById(id: Int): LiveData<Movie> {
//        return liveData {
//            EspressoIdlingResource.increment()
//            val movie = remoteDataSource.getMovieById(id)
//            emit(movie)
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    fun getTvShowById(id: Int): LiveData<Series> {
//        return liveData {
//            EspressoIdlingResource.increment()
//            val series = remoteDataSource.getTvShowById(id)
//            emit(series)
//            EspressoIdlingResource.decrement()
//        }
//    }



//    fun getUpcomingMovies(): LiveData<ApiResponse<List<Movie>>> {
//        return liveData {
//            EspressoIdlingResource.increment()
//            val movies = remoteDataSource.getUpcomingMovies()
//            emit(ApiResponse.success(movies))
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    fun getPopularTvShows(): LiveData<ApiResponse<List<Series>>> {
//        return liveData {
//            EspressoIdlingResource.increment()
//            val series = remoteDataSource.getPopularTvShows()
//            emit(ApiResponse.success(series))
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    fun getMovieById(id: Int): LiveData<ApiResponse<Movie>> {
//        return liveData {
//            EspressoIdlingResource.increment()
//            val movie = remoteDataSource.getMovieById(id)
//            emit(ApiResponse.success(movie))
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    fun getTvShowById(id: Int): LiveData<ApiResponse<Series>> {
//        return liveData {
//            EspressoIdlingResource.increment()
//            val series = remoteDataSource.getTvShowById(id)
//            emit(ApiResponse.success(series))
//            EspressoIdlingResource.decrement()
//        }
//    }



    fun getUpcomingMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource <List<MovieEntity>, List<Movie>>() {

            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getUpcomingMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                liveData {
                    EspressoIdlingResource.increment()
                    val movies = remoteDataSource.getUpcomingMovies()
                    emit(ApiResponse.success(movies))
                    EspressoIdlingResource.decrement()
                }

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.overview,
                        response.original_title,
                        response.poster_path,
                        response.vote_average,
                        response.release_date,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    fun getPopularTvShows(): LiveData<Resource<List<SeriesEntity>>> {
        return object : NetworkBoundResource <List<SeriesEntity>, List<Series>>() {

            override fun loadFromDB(): LiveData<List<SeriesEntity>> =
                localDataSource.getPopularTvShows()

            override fun shouldFetch(data: List<SeriesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Series>>> =
                liveData {
                    EspressoIdlingResource.increment()
                    val series = remoteDataSource.getPopularTvShows()
                    emit(ApiResponse.success(series))
                    EspressoIdlingResource.decrement()
                }

            override fun saveCallResult(data: List<Series>) {
                val tvShowList = ArrayList<SeriesEntity>()
                for (response in data) {
                    val tvShow = SeriesEntity(
                        response.id,
                        response.overview,
                        response.original_name,
                        response.poster_path,
                        response.vote_average,
                        response.number_of_episodes,
                        response.first_air_date,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    fun getMovieById(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, Movie>() {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieById(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> =
                liveData {
                    EspressoIdlingResource.increment()
                    val movie = remoteDataSource.getMovieById(id)
                    emit(ApiResponse.success(movie))
                    EspressoIdlingResource.decrement()
                }

            override fun saveCallResult(data: Movie) {
                TODO("Not yet implemented")
            }

        }.asLiveData()

    }

    fun getTvShowById(id: Int): LiveData<Resource<SeriesEntity>> {
        return object : NetworkBoundResource<SeriesEntity, Series>() {
            override fun loadFromDB(): LiveData<SeriesEntity> =
                localDataSource.getTvShowById(id)

            override fun shouldFetch(data: SeriesEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<Series>> =
                liveData {
                    EspressoIdlingResource.increment()
                    val series = remoteDataSource.getTvShowById(id)
                    emit(ApiResponse.success(series))
                    EspressoIdlingResource.decrement()
                }

            override fun saveCallResult(data: Series) {
                localDataSource
            }

        }.asLiveData()
    }

    fun getFavMovies(): LiveData<List<MovieEntity>> = localDataSource.getFavMovies()

    fun getFavTvShows(): LiveData<List<SeriesEntity>> = localDataSource.getFavTvShows()

//    fun setMovieAsFavorite(movie: MovieEntity, newState: Boolean) =
//        appExecutors.diskIO().execute { localDataSource.setMovieAsFavorite(movie, newState) }
//
//    fun setTvShowAsFavorite(tvShow: SeriesEntity, newState: Boolean) =
//        appExecutors.diskIO().execute { localDataSource.setTvShowAsFavorite(tvShow, newState) }


    suspend fun setMovieAsFavorite(movie: MovieEntity, newState: Boolean) =
        localDataSource.setMovieAsFavorite(movie, newState)

    suspend fun setTvShowAsFavorite(tvShow: SeriesEntity, newState: Boolean) =
        localDataSource.setTvShowAsFavorite(tvShow, newState)
}