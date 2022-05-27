package com.example.weathernah.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weathernah.ui.fragments.AFragment
import com.example.weathernah.ui.fragments.BFragment

class AdapterPager2(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> AFragment()
            1 -> BFragment()
            else -> throw IllegalStateException()
        }
        return fragment
    }


}