package com.example.speedtest.data.di


import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(RepositoriesModule::class))
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideLocationManager(context: Context) = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

}
