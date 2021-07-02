package com.example.nontonkuy.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.nontonkuy.R
import com.example.nontonkuy.utils.DataDummy
import com.example.nontonkuy.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummySeries = DataDummy.generateDummySeries()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions
            .scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_year)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_description)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun loadSeries() {
        onView(withText("TV Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_series))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_series)).perform(
            RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(dummySeries.size))
    }

    @Test
    fun loadDetailSeries() {
        onView(withText("TV Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_series_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_series_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_series_year)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_series_episodes)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.tv_series_description)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))

    }
}