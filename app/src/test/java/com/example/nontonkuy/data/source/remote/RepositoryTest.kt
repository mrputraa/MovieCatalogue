package com.example.nontonkuy.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nontonkuy.ui.movie.MainCoroutineRule
import com.example.nontonkuy.utils.DataDummy
import com.example.nontonkuy.utils.LiveDataTestUtils
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repo = FakeRepository(remote)

    private val movies = DataDummy.generateRemoteDummyMovies()
    private val movie = DataDummy.generateRemoteDummyMovies()[0]
    private val movieId = movie.id
    private val shows = DataDummy.generateRemoteDummySeries()
    private val show = DataDummy.generateRemoteDummySeries()[0]
    private val showId = show.id

    @Test
    fun getUpcomingMovies() = mainCoroutineRule.runBlockingTest{
        `when`(remote.getUpcomingMovies()).thenReturn(movies)
        val movieEntities = LiveDataTestUtils.getValue(repo.getUpcomingMovies())
        Mockito.verify(remote).getUpcomingMovies()
        assertNotNull(movieEntities)
        assertEquals(movies.size.toLong(), movies.size.toLong())
    }

    @Test
    fun getPopularTvShows() = mainCoroutineRule.runBlockingTest{
        `when`(remote.getPopularTvShows()).thenReturn(shows)
        val seriesEntities = LiveDataTestUtils.getValue(repo.getPopularTvShows())
        Mockito.verify(remote).getPopularTvShows()
        assertNotNull(seriesEntities)
        assertEquals(shows.size.toLong(), shows.size.toLong())
    }

    @Test
    fun getMovieById() = mainCoroutineRule.runBlockingTest {
        `when`(remote.getMovieById(movieId)).thenReturn(movie)
        val movieEntities = LiveDataTestUtils.getValue(repo.getMovieById(movieId))
        Mockito.verify(remote).getMovieById(movieId)
        assertNotNull(movieEntities)
        assertEquals(movie, movie)
    }

    @Test
    fun getTvShowById() = mainCoroutineRule.runBlockingTest {
        `when`(remote.getTvShowById(showId)).thenReturn(show)
        val seriesEntities = LiveDataTestUtils.getValue(repo.getTvShowById(showId))
        Mockito.verify(remote).getTvShowById(showId)
        assertNotNull(seriesEntities)
        assertEquals(show, show)

    }
}