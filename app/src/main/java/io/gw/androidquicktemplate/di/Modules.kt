package io.gw.androidquicktemplate.di

import io.gw.androidquicktemplate.remote.AppApiService
import io.gw.androidquicktemplate.ui.screen.home.HomeViewModel
import io.gw.androidquicktemplate.ui.screen.login.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://67f61743913986b16fa6a1e4.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(AppApiService::class.java)
    }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}
