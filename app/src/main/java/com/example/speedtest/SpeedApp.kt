package com.example.speedtest

import android.app.Application
import com.example.speedtest.presentation.di.AppComponent
import com.example.speedtest.presentation.di.AppModule
import com.example.speedtest.presentation.di.DaggerAppComponent

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class SpeedApp: Application() {

    companion object {
        var speedApp: SpeedApp? = null

        var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()

        speedApp = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent?.inject(this)

    }

}