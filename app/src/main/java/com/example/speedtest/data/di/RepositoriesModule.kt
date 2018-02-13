package com.example.speedtest.data.di


import android.content.Context
import android.location.LocationManager
import com.example.speedtest.data.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideSpeedCheckManager(context: Context, locationManager: LocationManager, speedRepository: SpeedRepository, graphRepository: GraphRepository) =
            SpeedCheckManager(context, locationManager, speedRepository, graphRepository)

    @Provides
    @Singleton
    fun provideNotificationsRepository(): NotificationsManager = NotificationsManagerImpl()

    @Provides
    @Singleton
    fun provideSpeedRepository(): SpeedRepository = SpeedRepositoryImpl()

    @Provides
    @Singleton
    fun provideGraphRepository(): GraphRepository = GraphRepositoryImpl()
}
