package com.example.nontonkuy.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repo: Repository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @ExperimentalCoroutinesApi
    @Test
    fun getUpcomingMovies() = mainCoroutineRule.runBlockingTest {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<Movie>>()
        movies.value = dummyMovies

        `when`(repo.getUpcomingMovies()).thenReturn(movies)
        viewModel = MovieViewModel(repo)
        val movieEntities = viewModel.movies.value
        verify(repo).getUpcomingMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.movies.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}