package com.example.speedtest.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import rx.Observable
import rx.subjects.PublishSubject

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class SpeedCheckManager(val context: Context,
                        val locationManager: LocationManager) {

    companion object {

    }

    private val speedSubject: PublishSubject<Float> = PublishSubject.create()

    private val locationListener = CustomLocationListener()

    fun listenForSpeed(): Observable<Float> {
        return speedSubject
    }

    fun runSpeedChecker() {
        subscribeToLocationChange()
    }

    @SuppressLint("MissingPermission")
    private fun subscribeToLocationChange() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
        }
    }

    private inner class CustomLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
                speedSubject.onNext(location.speed)
            }
        }

        override fun onProviderDisabled(provider: String) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

}