package com.example.speedtest.presentation.chart

import com.example.speedtest.data.db.entity.ChartPoint
import com.example.speedtest.presentation.Presenter

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
interface ChartPresenter<T>: Presenter<T> {

    interface ChartView {

        fun showGraphPoints(listValues: List<ChartPoint>)

    }

    fun getGraphPoints()

}