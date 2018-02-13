package com.example.speedtest.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.speedtest.data.entity.CompositeProviderState
import com.example.speedtest.data.models.SpeedInfoModel
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
                        val locationManager: LocationManager,
                        val speedRepository: SpeedRepository) {

    companion object {

    }

    private val checkIntervalSec = 30000L

    private val speedSubject: PublishSubject<SpeedInfoModel> = PublishSubject.create()

    private val locationListener = CustomLocationListener()

    private var reconnectSubscription = Subscriptions.empty()

    fun listenForSpeed(): Observable<SpeedInfoModel> {
        return speedSubject
    }

    fun runSpeedChecker() {
        getSpeedInfoModel()
    }

    fun clean() {
        reconnectSubscription.unsubscribe()
        locationManager.removeUpdates(locationListener)
        speedSubject.onCompleted()
    }

    private fun getSpeedInfoModel() {
        speedRepository.getSpeedInfo().subscribe(
                {
                    localSpeedInfo = it
                    subscribeToLocationChange()
                },
                {Log.d("onxGetSpeedInfo", "Err: $it")}
        )
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

    private var lastLocation: Location? = null
    private var localSpeedInfo = SpeedInfoModel(0, 0, 0)

    private inner class CustomLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
                Toast.makeText(context, "Location changed", Toast.LENGTH_SHORT).show()
                if (!location.hasSpeed()) {
                    return
                }
                val speedInfoModel = calculateDistanceAndSpeed(location)
                localSpeedInfo = speedInfoModel
                speedSubject.onNext(speedInfoModel)
                speedRepository.setSpeedInfo(speedInfoModel)
            }
        }

        override fun onProviderDisabled(provider: String) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    private fun calculateDistanceAndSpeed(location: Location): SpeedInfoModel {
        val speedInfoModel = localSpeedInfo
        if (lastLocation != null) {
            val distance = location.distanceTo(lastLocation)
            val speed = location.speed.convertMeterPerSecondToKmPerHour()
            speedInfoModel.totalDistance = distance.toInt() + speedInfoModel.totalDistance
            if (speed > speedInfoModel.maxSpeed) {
                speedInfoModel.maxSpeed = speed
            }
            speedInfoModel.currentSpeed = speed
        }
        lastLocation = location
        return speedInfoModel
    }

}