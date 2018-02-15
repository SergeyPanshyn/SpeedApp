package com.example.speedtest.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.speedtest.data.db.entity.ChartPoint
import com.example.speedtest.data.db.entity.SpeedInfo
import com.example.speedtest.data.entity.CompositeProviderState
import com.example.speedtest.extention.convertMeterPerSecondToKmPerHour
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class SpeedCheckManager(val context: Context,
                        val locationManager: LocationManager,
                        val speedRepository: SpeedRepository,
                        val graphRepository: ChartRepository) {

    companion object {

    }

    private val checkIntervalSec = 30000L

    private var lastLocation: Location? = null

    private var localSpeedInfo = SpeedInfo(1, 0, 0, 0)

    private val speedSubject: PublishSubject<SpeedInfo> = PublishSubject.create()

    private val locationListener = CustomLocationListener()

    private var reconnectSubscription = Disposables.empty()

    fun listenForSpeed(): Observable<SpeedInfo> {
        return speedSubject.doOnSubscribe { Log.d("testChecker", "Subscibed") }
    }

    fun runSpeedChecker() {
        Log.d("testChecker", "runSpeedChecker")
        getSpeedInfoModel()
    }

    fun clean() {
        reconnectSubscription.dispose()
        locationManager.removeUpdates(locationListener)
        speedSubject.onComplete()
    }

    private fun getSpeedInfoModel() {
        Log.d("testChecker", "getSpeedInfoModel")
        subscribeToLocationChange()
        speedRepository.getSpeedInfo().subscribe(
                {
                    localSpeedInfo = it
                    subscribeToLocationChange()
                    Log.d("testChecker", "getSpeedInfo")
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
                    Log.d("testChecker", "1")
                    locationManager.removeUpdates(locationListener)
                    if (gpsState) {
                        Log.d("testChecker", "2")
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
                        return@subscribe
                    }
                    if (networkState) {
                        Log.d("testChecker", "3")
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
                    }
                    Log.d("testChecker", "4")
                },
                        {
                             Log.i("onxLocationChangeErr", it.toString())
                        }
                )
    }

    private inner class CustomLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            Log.d("testChecker", "op")
            if (location != null) {
                Toast.makeText(context, "Location changed", Toast.LENGTH_SHORT).show()

                val speedInfoModel = calculateDistanceAndSpeed(location)
                localSpeedInfo = speedInfoModel
                speedSubject.onNext(speedInfoModel)
                speedRepository.setSpeedInfo(speedInfoModel)
                graphRepository.saveGraphPoint(ChartPoint(System.currentTimeMillis(), speedInfoModel.currentSpeed))
            }
        }

        override fun onProviderDisabled(provider: String) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    private fun calculateDistanceAndSpeed(location: Location): SpeedInfo {
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