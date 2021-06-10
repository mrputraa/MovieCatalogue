package com.example.nontonkuy.data

data class MovieEntity (
    val movieId: String,
    val title: String,
    val releaseYear: String,
    val desc: String,
    val rating: Double,
    val genre: String,
    val favorited: Boolean = false,
    val posterPath: String
    )