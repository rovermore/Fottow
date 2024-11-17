package com.fottow.fottow

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FottowApplication : Application() {
    companion object {
        @JvmStatic
        lateinit var app: FottowApplication
    }

    override fun onCreate() {
        super.onCreate()

        app = this
    }
}