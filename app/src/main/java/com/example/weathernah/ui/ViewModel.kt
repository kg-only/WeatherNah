package com.example.weathernah.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernah.api.Repository
import com.example.weathernah.models.Weather
import com.example.weathernah.models.WeatherDataClass
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class ViewModel(private val repo: Repository) : ViewModel() {

    val dataList = MutableLiveData<List<WeatherDataClass>>()
    private val data = MutableLiveData<WeatherDataClass>()
    private val dataWeather = MutableLiveData<List<Weather>>()
    private var searchJob: Job? = null

    fun loadData(city: String) = viewModelScope.launch {
        try {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    dataList.value = listOf(repo.getWeather(city))
                }
            Timber.e("SUCCESS")
        } catch (e: Exception) {
            Timber.e(e.toString())
        }
    }
}

//    fun loadDataLonLat(lat: Double, lon: Double): MutableLiveData<WeatherDataClass> {
//        viewModelScope.launch {
//            try {
////                searchJob?.cancel()
////                searchJob = viewModelScope.launch {
////                    delay(5000)
//
//                data.value = repo.getWeatherLonLat(lat,lon)
////                }
//                Log.e("###", "SUCCESS")
//            } catch (e: Exception) {
//                Log.e("###", e.toString())
//            }
//        }
//        return data
//    }

