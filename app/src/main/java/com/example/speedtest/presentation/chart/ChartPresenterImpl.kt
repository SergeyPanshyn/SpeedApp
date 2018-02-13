package com.example.speedtest.presentation.chart

import android.util.Log
import com.example.speedtest.domain.chart.GetChartPointsUseCase

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class ChartPresenterImpl<T: ChartPresenter.ChartView>(private val getChartPointsUseCase: GetChartPointsUseCase): ChartPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

    override fun getGraphPoints() {
        getChartPointsUseCase.executeSingle(
                {view?.showGraphPoints(it)},
                { Log.d("getChartPoints", "Err: $it")}
        )
    }
}