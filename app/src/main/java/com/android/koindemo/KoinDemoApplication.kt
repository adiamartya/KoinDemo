package com.android.koindemo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * start koin for dependency injection
         */
        startKoin {
            androidLogger()
            androidContext(this@KoinDemoApplication)
            modules(listOf(appModule, viewModelModule, repoModule))
        }
    }
}