package com.example.mermelalogistic

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class LogisticApp : Application(){
    override fun onCreate() {
        super.onCreate()
        setTimber()
    }

    private fun setTimber(){
        if(BuildConfig.ENABLE_LOG){
            Timber.plant(Timber.DebugTree())
        }
    }
}