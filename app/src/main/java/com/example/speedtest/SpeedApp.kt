package com.example.speedtest

import android.app.Application
import com.example.speedtest.presentation.di.AppComponent
import com.example.speedtest.presentation.di.AppModule
import com.example.speedtest.presentation.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

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

        configureRealm()

        speedApp = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent?.inject(this)

    }

    private fun configureRealm() {
        Realm.init(this)

        val realmConfig = RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(realmConfig)
    }

}