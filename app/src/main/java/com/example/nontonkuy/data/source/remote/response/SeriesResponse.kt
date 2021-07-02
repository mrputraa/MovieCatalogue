package com.example.nontonkuy.data.source.remote.response

import android.os.Parcelable
import com.example.nontonkuy.data.source.remote.Series
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesResponse(
    val results: List<Series>
): Parcelable