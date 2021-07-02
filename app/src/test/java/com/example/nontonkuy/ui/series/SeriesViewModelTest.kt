package com.example.nontonkuy.ui.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.data.source.remote.Series
import kotlinx.coroutines.test.runBlockingTest
import com.example.nontonkuy.ui.movie.MainCoroutineRule
import com.example.nontonkuy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SeriesViewModelTest {
    private lateinit var viewModel: SeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repo: Repository

    @Mock
    private lateinit var observer: Observer<List<Series>>

    @ExperimentalCoroutinesApi
    @Test
    fun getPopularTvShows() = mainCoroutineRule.runBlockingTest {
        val dummySeries = DataDummy.generateDummySeries()
        val series = MutableLiveData<List<Series>>()
        series.value = dummySeries

        Mockito.`when`(repo.getPopularTvShows()).thenReturn(series)
        viewModel = SeriesViewModel(repo)
        val seriesEntities = viewModel.show.value
        verify(repo).getPopularTvShows()
        assertNotNull(seriesEntities)
        assertEquals(10, seriesEntities?.size)

        viewModel.show.observeForever(observer)
        verify(observer).onChanged(dummySeries)
    }
}