package me.randheer.covidstatsin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class CovidStatsSharedApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var appCtx: Context
    }

    override fun onCreate() {
        super.onCreate()
        appCtx = applicationContext
    }
}