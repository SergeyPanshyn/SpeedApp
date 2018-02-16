package com.example.speedtest.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.example.speedtest.R
import com.example.speedtest.data.db.entity.ChartPoint
import com.example.speedtest.data.db.entity.SpeedInfo
import com.example.speedtest.extention.convertMeterPerSecondToKmPerHour
import com.example.speedtest.extention.getProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import io.reactivex.subjects.PublishSubject

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
        return speedSubject
    }

    fun runSpeedChecker() {
        getSpeedInfoModel()
    }

    fun clean() {
        reconnectSubscription.dispose()
        locationManager.removeUpdates(locationListener)
        speedSubject.onComplete()
    }

    private fun getSpeedInfoModel() {
        subscribeToLocationChange()
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
        locationManager.removeUpdates(locationListener)
        when (context.getProvider()) {
            context.getString(R.string.provider_gps) -> { locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener) }
            context.getString(R.string.provider_network) -> { locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener) }
        }
    }

    fun changeProvider(): Completable {
        subscribeToLocationChange()
        return Completable.complete()
    }

    private inner class CustomLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
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