package com.example.speedtest.presentation.dashboard

import com.example.speedtest.presentation.Presenter

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
interface DashboardPresenter<T>: Presenter<T> {

    interface DashboardView {

        fun showSpeed(speed: Int)

    }

    fun subscribeToCurrentSpeed()

}