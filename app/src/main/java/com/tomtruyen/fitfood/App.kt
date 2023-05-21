package com.tomtruyen.fitfood

import android.app.Application
import com.tomtruyen.fitfood.managers.ResourceProvider

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        ResourceProvider.initialize(this)
    }
}