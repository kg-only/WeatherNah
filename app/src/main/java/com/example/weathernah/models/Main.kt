package com.example.weathernah.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Main(
val temp:Float,
val feels_like:Float,
val temp_min:Float,
val temp_max:Float,
val pressure:Int,
val humidity:Int,
): Parcelable
