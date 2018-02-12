package com.example.speedtest.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.example.speedtest.data.entity.CompositeProviderState
import com.example.speedtest.extention.convertMeterPerSecondToKmPerHour
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.PublishSubject
import rx.subscriptions.Subscriptions
import java.util.concurrent.TimeUnit

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class SpeedCheckManager(val context: Context,
                        val locationManager: LocationManager) {

    companion object {

    }

    private val checkIntervalSec = 30000L

    private val speedSubject: PublishSubject<Int> = PublishSubject.create()

    private val locationListener = CustomLocationListener()

    private var reconnectSubscription = Subscriptions.empty()

    fun listenForSpeed(): Observable<Int> {
        return speedSubject
    }

    fun runSpeedChecker() {
        subscribeToLocationChange()
    }

    @SuppressLint("MissingPermission")
    private fun subscribeToLocationChange() {
        reconnectSubscription = Observable.interval(checkIntervalSec, TimeUnit.MILLISECONDS)
                .map { CompositeProviderState(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER), locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) }
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe({(gpsState, networkState) ->
                    locationManager.removeUpdates(locationListener)
                    if (gpsState) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
                        return@subscribe
                    }
                    if (networkState) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
                    }
                },
                        {
                             Log.i("onxLocationChangeErr", it.toString())
                        }
                )
    }

    private inner class CustomLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
                if (!location.hasSpeed()) {
                    return
                }

                speedSubject.onNext(location.speed.convertMeterPerSecondToKmPerHour())
            }
        }

        override fun onProviderDisabled(provider: String) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

}