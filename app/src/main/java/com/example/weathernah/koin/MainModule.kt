package com.example.weathernah.koin

import com.example.weathernah.api.Repository
import com.example.weathernah.ui.ViewModel
import org.koin.android.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object MainModule {
    fun create():Module = module {
        viewModel { ViewModel(get()) }
//        single { Repository(get()) }
        single { Repository(get()) }
    }
}