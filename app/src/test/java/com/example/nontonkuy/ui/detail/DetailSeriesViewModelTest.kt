package com.example.nontonkuy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.data.source.remote.Series
import com.example.nontonkuy.ui.movie.MainCoroutineRule
import com.example.nontonkuy.utils.DataDummy
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
class DetailSeriesViewModelTest {
    private lateinit var viewModel: DetailSeriesViewModel
    private val dummySeries = DataDummy.generateDummySeries()[0]
    private val id = dummySeries.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repo: Repository

    @Mock
    private lateinit var observer: Observer<Series>

    @Test
    fun getTvShowById() = mainCoroutineRule.runBlockingTest {
        val show = MutableLiveData<Series>()
        show.value = dummySeries

        `when`(repo.getTvShowById(id)).thenReturn(show)
        viewModel = DetailSeriesViewModel(repo, id)
        val seriesEntity = viewModel.tv.value
        assertNotNull(seriesEntity)
        assertEquals(dummySeries.id, seriesEntity?.id)
        assertEquals(dummySeries.poster_path, seriesEntity?.poster_path)
        assertEquals(dummySeries.original_name, seriesEntity?.original_name)
        assertEquals(dummySeries.vote_average.toString(), seriesEntity?.vote_average.toString())
        assertEquals(dummySeries.first_air_date, seriesEntity?.first_air_date)
        assertEquals(dummySeries.overview, seriesEntity?.overview)

        viewModel.tv.observeForever(observer)
        Mockito.verify(observer).onChanged(dummySeries)
    }
}