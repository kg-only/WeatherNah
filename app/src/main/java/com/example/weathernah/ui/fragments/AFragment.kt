package com.example.weathernah.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathernah.R
import com.example.weathernah.databinding.FragmentABinding
import com.example.weathernah.ui.ViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class AFragment : Fragment(R.layout.fragment_a) {
    private val binding by viewBinding(FragmentABinding::bind)
    private val vm by sharedViewModel<ViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            vm.dataList.observe(viewLifecycleOwner) {
                val data = it.first()
                val weather = data.weather.first()
                wind.text = "Скорость ветра: " + data.wind.speed
                sunrise.text = "Рассвет: " + getDateTime(data.sys.sunrise)
                sunset.text = "Закат: " + getDateTime(data.sys.sunset)
                preasure.text = "Давление: " + data.main.pressure
                lat.text = "Широта: " + data.coord.lat
                lon.text = "Долгота: " + data.coord.lon
            }
        }
    }



    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(time: Long): String {
        val format = "HH:mm:ss" // you can add the format you need
//        val format = "dd MMM yyyy HH:mm:ss" // you can add the format you need
        val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
//        sdf.timeZone = TimeZone.getDefault() // set anytime zone you need
        return sdf.format(Date(time * 1000))
    }
}