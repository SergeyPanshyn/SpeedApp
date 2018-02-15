package com.example.speedtest.data.di


import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.preference.PreferenceManager
import com.example.speedtest.data.db.SpeedDatabase
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

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room.databaseBuilder(context, SpeedDatabase::class.java, "my_db").allowMainThreadQueries().build()

}
