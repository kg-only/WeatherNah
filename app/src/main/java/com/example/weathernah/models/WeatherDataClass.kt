package com.example.weathernah.models

import android.os.Parcelable
import com.example.weathernah.models.Clouds
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDataClass(
    val weather: List<Weather>,
    val coord: Coord,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val code: Int
):Parcelable
