package com.example.nontonkuy.data

data class SeriesEntity(
    val seriesId: String,
    val title: String,
    val startYear: String,
    val finishYear: String?,
    val episodes: Int,
    val desc: String,
    val rating: Double,
    val genre: String,
    val favorited: Boolean = false,
    val posterPath: String
)