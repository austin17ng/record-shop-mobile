package io.gw.recordshop.di

import io.gw.recordshop.BuildConfig
import io.gw.recordshop.remote.AppApiService
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.screen.album.AlbumDetailsViewModel
import io.gw.recordshop.ui.screen.cart.CartViewModel
import io.gw.recordshop.ui.screen.home.HomeViewModel
import io.gw.recordshop.ui.screen.login.LoginViewModel
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

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(RecordShopApiService::class.java)
    }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AlbumDetailsViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}
