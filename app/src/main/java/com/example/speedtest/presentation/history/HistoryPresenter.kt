package com.example.speedtest.presentation.history

import com.example.speedtest.presentation.Presenter

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
interface HistoryPresenter<T>: Presenter<T> {

    interface HistoryView {

        fun showTrackList(data: List<String>)

    }

    fun getTrackList()

}