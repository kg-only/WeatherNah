package com.example.weathernah.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import timber.log.Timber

abstract class LocationUtil(val context: Activity) {
    private var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val manager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(context)) {
            Timber.d("huzzy Location 12")
            locationUpdate()
        } else if (!hasGPSDevice(context)) {
            Timber.d("huzzy Location 13")
            //Phone doesnt have GPS
        } else if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(context)) {
            Timber.d("huzzy Location 14")
            requestLocationUpdates() // gps didnt activate yet
        }
    }

    private fun requestLocationUpdates() {
        val builder: LocationSettingsRequest.Builder? = LocationSettingsRequest.Builder()
            .addLocationRequest(getLocationRequest())
            .setAlwaysShow(true)

        val client = LocationServices.getSettingsClient(context)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder!!.build())
        task.addOnSuccessListener(context) {

        }.addOnFailureListener {
            if (it is ResolvableApiException) {
//                it.startResolutionForResult(context, 200)
                requestLocationPermission(it)
            }
        }
    }

    private fun hasGPSDevice(context: Context): Boolean {
        val mgr: LocationManager = context
            .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers: List<String> = mgr.allProviders
        return providers.contains(LocationManager.GPS_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    fun locationUpdate() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                val location: Location = p0.lastLocation ?: return
                //    val latLng = LatLng(location.latitude, location.longitude)
                getLocation(location)
                fusedLocationClient.removeLocationUpdates(locationCallback) //get only 1 time location :)

            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
                if (!p0.isLocationAvailable) {
                    requestLocationUpdates()
                }
            }
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            it?.let {
                getLocation(it)
            } ?: apply {
                fusedLocationClient.requestLocationUpdates(
                    getLocationRequest(),
                    locationCallback,
                    Looper.myLooper()!!
                )
            }
        }
    }

    private fun getLocationRequest(): LocationRequest {
        val locationRequest: LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 200 //5 seconds interval
        return locationRequest
    }

    abstract fun getLocation(latlng: Location)
    abstract fun requestLocationPermission(it: ResolvableApiException)
}
