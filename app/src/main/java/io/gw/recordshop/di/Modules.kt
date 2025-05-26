package io.gw.recordshop.di

import android.content.Context
import io.gw.recordshop.BuildConfig
import io.gw.recordshop.Tags
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.screen.album.AlbumDetailsViewModel
import io.gw.recordshop.ui.screen.cart.CartViewModel
import io.gw.recordshop.ui.screen.home.HomeViewModel
import io.gw.recordshop.ui.screen.login.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val sharedPref = androidContext().getSharedPreferences(Tags.SHARED_PREF_NAME, Context.MODE_PRIVATE)


        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.toString()

                val isHeaderNeeded = !(
                        url.contains("/login")
                        || url.contains("/register")
                        || url.contains("/api/albums/")
                )

                val newRequest = if (isHeaderNeeded) {
                    val token = sharedPref.getString(Tags.SHARED_PREF_TOKEN_KEY, "") ?: ""
                    request.newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                } else {
                    request
                }

                chain.proceed(newRequest)
            }
            .addInterceptor(logging)
            .build()
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
