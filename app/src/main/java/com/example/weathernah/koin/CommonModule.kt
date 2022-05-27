package com.example.weathernah.koin

import com.example.weathernah.api.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String =
    "https://api.openweathermap.org/"

//private const val BASE_URL: String =
//    "https://api.weather.yandex.ru/"

//"https://api.openweathermap.org/data/2.5/weather/"

object CommonModule {
    fun create() = module {
        single {
            ///OkHTTP
            val interceptor = HttpLoggingInterceptor()//read about this
            interceptor.level = HttpLoggingInterceptor.Level.BODY//read about this
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            //retrofit
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            retrofit.create(RetrofitService::class.java)
        }
    }
}