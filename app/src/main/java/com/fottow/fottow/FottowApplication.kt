package com.fottow.fottow

import android.app.Application
import com.fottow.fottow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import androidx.work.Configuration
import org.koin.androidx.workmanager.koin.workManagerFactory

class FottowApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FottowApplication)
            modules(appModule)
        }
    }
}