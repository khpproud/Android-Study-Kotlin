package com.patrick.ankonetworkexample

import android.app.Application

class App: Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(this)
        super.onCreate()

    }
}

val prefs: Prefs by lazy {
    App.prefs!!
}