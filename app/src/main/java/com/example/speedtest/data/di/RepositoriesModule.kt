package com.example.speedtest.data.di


import android.content.Context
import android.location.LocationManager
import com.example.speedtest.data.repository.NotificationsManager
import com.example.speedtest.data.repository.NotificationsManagerImpl
import com.example.speedtest.data.repository.SpeedCheckManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideSpeedCheckManager(context: Context, locationManager: LocationManager) =
            SpeedCheckManager(context, locationManager)

    @Provides
    @Singleton
    fun provideNotificationsRepository(): NotificationsManager = NotificationsManagerImpl()
}
