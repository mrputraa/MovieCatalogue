package com.example.nontonkuy.data.source.remote.response

import android.os.Parcelable
import com.example.nontonkuy.data.source.remote.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val results: List<Movie>
): Parcelable