package com.example.speedtest.presentation.dashboard

import com.example.speedtest.data.models.SpeedInfoModel
import com.example.speedtest.presentation.Presenter

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
interface DashboardPresenter<T>: Presenter<T> {

    interface DashboardView {

        fun showSpeed(speedInfo: SpeedInfoModel)

        fun showSpeedInfo(speedInfoModel: SpeedInfoModel)

    }

    fun subscribeToCurrentLocation()

    fun getSpeedInfoModel()
//
//    fun setSpeedModelInfo(speedInfoModel: SpeedInfoModel)
}