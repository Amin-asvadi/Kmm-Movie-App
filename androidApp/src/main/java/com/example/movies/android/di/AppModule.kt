package com.example.movies.android.di

import com.example.movies.android.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{
        HomeScreenViewModel(get())
    }
}