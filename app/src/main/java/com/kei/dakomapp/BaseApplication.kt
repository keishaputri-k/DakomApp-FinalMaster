package com.kei.dakomapp

import android.app.Application
import com.kei.dakomapp.util.ContextProvider

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ContextProvider.initialize(applicationContext)
    }
}