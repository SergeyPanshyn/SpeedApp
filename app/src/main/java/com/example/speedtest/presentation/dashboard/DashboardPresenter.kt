package com.example.speedtest.presentation.dashboard

import com.example.speedtest.data.db.entity.SpeedInfo
import com.example.speedtest.presentation.Presenter

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
interface DashboardPresenter<T>: Presenter<T> {

    interface DashboardView {

        fun showSpeed(speedInfo: SpeedInfo)

        fun showSpeedInfo(speedInfoModel: SpeedInfo)

    }

    fun subscribeToCurrentLocation()

    fun getSpeedInfoModel()

}