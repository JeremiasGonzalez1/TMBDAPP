package com.jg.tmbdapp

import android.app.Application
import com.jg.tmbdapp.presentation.DI.moduleHome
import com.jg.tmbdapp.presentation.DI.moduleHomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TMBDApp():Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TMBDApp)
            modules(moduleHome, moduleHomeViewModel)
        }
    }
}