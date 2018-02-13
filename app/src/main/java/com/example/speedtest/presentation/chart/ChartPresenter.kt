package com.example.speedtest.presentation.chart

import com.example.speedtest.data.models.GraphPointModel
import com.example.speedtest.presentation.Presenter

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
interface ChartPresenter<T>: Presenter<T> {

    interface ChartView {

        fun showGraphPoints(listValues: List<GraphPointModel>)

    }

    fun getGraphPoints()

}