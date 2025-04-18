 package io.gw.androidquicktemplate

import android.app.Application
import io.gw.androidquicktemplate.di.appModule
import io.gw.androidquicktemplate.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        setupKoin()
    }

    fun setupKoin() {
        startKoin {
            androidContext(this@AppApplication)
            modules(appModule, viewModelModule)
        }
    }
}