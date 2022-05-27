package com.example.weathernah.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sys(
    val type:Int,
    val id:Int,
    val country:String,
    val sunrise:Long,
    val sunset:Long
): Parcelable
