package com.example.weathernah.api

import com.example.weathernah.models.WeatherDataClass
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    //    https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=5d120d716ff645fd1975314e1f1c78cd&units=metric&lang=ru

    @GET("data/2.5/weather?appid=5d120d716ff645fd1975314e1f1c78cd&units=metric&lang=ru")
    suspend fun getWeatherList(@Query("q") city: String): WeatherDataClass

    @GET("data/2.5/weather?lat={lat}&lon={lon}&appid=5d120d716ff645fd1975314e1f1c78cd&units=metric&lang=ru")
    suspend fun getWeatherLonLat(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double
    ): WeatherDataClass


    @GET("v2/informers?lat=74.59&lon=42.87&lang=ru_RU")
//    https://api.weather.yandex.ru/v2/informers?lat=74.59&lon=42.87&lang=ru_RU
//   https://api.weather.yandex.ru/ base
    suspend fun getWeather(
        @Header("X-Yandex-API-Key:") key: String,
        @Path("lat") lat: Double, @Path("lon") lon: Double
    ): WeatherDataClass
}
