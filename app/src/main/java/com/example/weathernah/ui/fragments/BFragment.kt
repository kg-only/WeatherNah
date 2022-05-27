package com.example.weathernah.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathernah.R
import com.example.weathernah.databinding.FragmentBBinding
import com.example.weathernah.databinding.FragmentWeatherBinding
import com.example.weathernah.ui.ViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class BFragment : Fragment(R.layout.fragment_b) {
    private val binding by viewBinding(FragmentBBinding::bind)
    private val vm by sharedViewModel<ViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.dataList.observe(viewLifecycleOwner) {
            binding.tomorrow.text =
                "А завтра " + (getDate(it.first().sys.sunset).toInt() + 1) + " " + getDatee(it.first().sys.sunset)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(time: Long): String {
        val format = "dd" // you can add the format you need
//        val format = "dd MMM yyyy HH:mm:ss" // you can add the format you need
        val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
//        sdf.timeZone = TimeZone.getDefault() // set anytime zone you need
        return sdf.format(Date(time * 1000))
    }
    @SuppressLint("SimpleDateFormat")
    private fun getDatee(time: Long): String {
        val format = "MM yyyy" // you can add the format you need
//        val format = "dd MMM yyyy HH:mm:ss" // you can add the format you need
        val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
//        sdf.timeZone = TimeZone.getDefault() // set anytime zone you need
        return sdf.format(Date(time * 1000))
    }
}