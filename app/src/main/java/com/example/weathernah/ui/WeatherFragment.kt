package com.example.weathernah.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.telephony.CellLocation
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathernah.LocationServiceEnable
import com.example.weathernah.R
import com.example.weathernah.adapter.AdapterPager2
import com.example.weathernah.databinding.FragmentWeatherBinding
import com.example.weathernah.hasPermissions
import com.example.weathernah.requestPermissions
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val vm by sharedViewModel<ViewModel>()
    private val tabNames = listOf("Сегодня", "Завтра")
    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        with(binding) {
            try {
                vm.loadData("bishkek")
            } catch (e: Exception) {
                Log.e("####", e.toString())
            }

            vm.dataList.observe(viewLifecycleOwner) {
                val data = it.first()
                val weather = data.weather.first()
                date.text = "Дата: " + (getDate(data.sys.sunrise))
                desc.text = "Описание: " + weather.description
                mainTemp.text = data.main.temp.toString()
                citiName.text = data.name
                country.text = data.sys.country
                when (weather.icon) {
                    "01d" -> mainImage.setImageResource(R.drawable.sunny)
                    "02d" -> mainImage.setImageResource(R.drawable.weather)
                    "03d" -> mainImage.setImageResource(R.drawable.cloud)
                    "04d" -> mainImage.setImageResource(R.drawable.cloud_rain)
                    "09d" -> mainImage.setImageResource(R.drawable.rain)
                    "10d" -> mainImage.setImageResource(R.drawable.cloud_rain)
                    "11d" -> mainImage.setImageResource(R.drawable.cloud_storm)
                    "13d" -> mainImage.setImageResource(R.drawable.snowing)
                    else -> {
                        mainImage.setImageResource(R.drawable.full_moon)
                        hz.text = "Хз чё за погода..."
                    }
                }

            }
        }

        object : LocationUtil(requireActivity()) {
            override fun getLocation(latlng: Location) {
                Log.e("######", latlng.latitude.toString())
                Log.e("######", latlng.longitude.toString())
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun requestLocationPermission(it: ResolvableApiException) {
                when {
                    requireActivity().hasPermissions(locationPermission) -> requestLocationService()
                    else -> {
                        requestPermissions(locationPermission, requestCode = 1)
                    }
                }
            }
        }
    }

    private fun requestLocationService() {
        if (requireActivity().LocationServiceEnable()) CellLocation.requestLocationUpdate()
    }

    private fun initAdapter() {
        binding.pager.adapter = AdapterPager2(requireActivity())
        TabLayoutMediator(binding.tabView, pager) { tabView, pager ->
            tabView.text = tabNames[pager]
        }.attach()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(time: Long): String {
        val format = "dd MMM yyyy" // you can add the format you need
//        val format = "dd MMM yyyy HH:mm:ss" // you can add the format you need
        val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
//        sdf.timeZone = TimeZone.getDefault() // set anytime zone you need
        return sdf.format(Date(time * 1000))
    }
}



