package com.example.weathernah.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coord(
    val lon: Float,
    val lat: Float
): Parcelable
