package com.example.weathernah.api

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.Size
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernah.api.Repository
import com.example.weathernah.models.WeatherDataClass
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Activity.requestPermissions(@Size(min = 1) vararg permissions: String, requestCode: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
    this.requestPermissions(permissions, requestCode)
}
fun Context.hasPermissions(@Size(min = 1) vararg permission: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
    return permission.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}
fun Activity.shouldShowCustomPermissionRequestHint(permission: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false
    return !shouldShowRequestPermissionRationale(permission)
}