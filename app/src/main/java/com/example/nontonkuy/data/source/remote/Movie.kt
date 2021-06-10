package com.example.nontonkuy.data.source.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val id: Int,
    val overview: String?,
    val original_title: String,
    val poster_path: String,
    val vote_average: Double,
    val release_date: String
): Parcelable