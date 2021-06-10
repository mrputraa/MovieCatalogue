package com.example.nontonkuy.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.nontonkuy.R
import com.example.nontonkuy.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummySeries = DataDummy.generateDummySeries()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions
            .scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.tv_movie_title))
            .check(ViewAssertions.matches(withText(dummyMovie[0].title)))
        Espresso.onView(withId(R.id.tv_movie_genres))
            .check(ViewAssertions.matches(withText(dummyMovie[0].genre)))
        Espresso.onView(withId(R.id.tv_movie_rating))
            .check(ViewAssertions.matches(withText(dummyMovie[0].rating.toString())))
        Espresso.onView(withId(R.id.tv_movie_year))
            .check(ViewAssertions.matches(withText(dummyMovie[0].releaseYear)))
        Espresso.onView(withId(R.id.tv_movie_description))
            .check(ViewAssertions.matches(withText(dummyMovie[0].desc)))
    }

    @Test
    fun loadSeries() {
        Espresso.onView(withText("TV Series")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_series))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_series)).perform(
            RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(dummySeries.size))
    }

    @Test
    fun loadDetailSeries() {
        Espresso.onView(withText("TV Series")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_series)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.tv_series_title))
            .check(ViewAssertions.matches(withText(dummySeries[0].title)))
        Espresso.onView(withId(R.id.tv_series_genres))
            .check(ViewAssertions.matches(withText(dummySeries[0].genre)))
        Espresso.onView(withId(R.id.tv_series_rating))
            .check(ViewAssertions.matches(withText(dummySeries[0].rating.toString())))
        Espresso.onView(withId(R.id.tv_series_episodes))
            .check(ViewAssertions.matches(withText(dummySeries[0].episodes.toString())))
        Espresso.onView(withId(R.id.tv_series_year))
            .check(ViewAssertions.matches(withText("${dummySeries[0].startYear}-${dummySeries[0].finishYear}")))
        Espresso.onView(withId(R.id.tv_series_description))
            .check(ViewAssertions.matches(withText(dummySeries[0].desc)))
    }
}