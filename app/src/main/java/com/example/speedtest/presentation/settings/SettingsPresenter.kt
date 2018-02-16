package com.example.speedtest.presentation.settings

import com.example.speedtest.presentation.Presenter

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
interface SettingsPresenter<T>: Presenter<T> {

    interface SettingsView {

    }

    fun clearDatabase()

    fun changeProvider()

}