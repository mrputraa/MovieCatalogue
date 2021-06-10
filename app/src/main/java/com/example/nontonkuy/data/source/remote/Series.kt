package com.example.nontonkuy.data.source.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(
    val id: Int,
    val overview: String?,
    val original_name: String,
    val poster_path: String,
    val vote_average: Double,
    val number_of_episodes: Int?,
    val first_air_date: String
): Parcelable