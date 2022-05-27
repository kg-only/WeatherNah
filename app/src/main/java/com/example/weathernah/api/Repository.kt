package com.example.weathernah.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val api: RetrofitService) {
    suspend fun getWeather(city: String) = withContext(Dispatchers.IO) {
      api.getWeatherList(city)
    }
    suspend fun getWeatherLonLat(lat: Double, lon: Double) = withContext(Dispatchers.IO) {
        api.getWeatherLonLat(lat,lon)
        //        lat 74.59
//        lon 42.87

    }
    suspend fun test()= withContext(Dispatchers.IO) {
        api.getWeather("ca809ba6-4798-4e25-9b77-f5298efe4f0a",74.59,42.87)
    }

}
