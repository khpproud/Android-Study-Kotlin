package com.patrick.ankosqliteexample

import android.app.Application
import com.facebook.stetho.Stetho

class NewApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}