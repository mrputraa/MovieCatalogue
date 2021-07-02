package com.example.nontonkuy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.ui.movie.MainCoroutineRule
import com.example.nontonkuy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val id = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repo: Repository

    @Mock
    private lateinit var observer: Observer<Movie>

    @Test
    fun getMovieById() = mainCoroutineRule.runBlockingTest{
        val movie = MutableLiveData<Movie>()
        movie.value = dummyMovie

        `when`(repo.getMovieById(id)).thenReturn(movie)
        viewModel = DetailMovieViewModel(repo, id)
        val movieEntity = viewModel.movie.value
        verify(repo).getMovieById(id)
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.poster_path, movieEntity?.poster_path)
        assertEquals(dummyMovie.original_title, movieEntity?.original_title)
        assertEquals(dummyMovie.vote_average.toString(), movieEntity?.vote_average.toString())
        assertEquals(dummyMovie.release_date, movieEntity?.release_date)
        assertEquals(dummyMovie.overview, movieEntity?.overview)

        viewModel.movie.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}