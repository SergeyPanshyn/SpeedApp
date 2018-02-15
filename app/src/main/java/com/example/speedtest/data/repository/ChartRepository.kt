package com.example.speedtest.data.repository

import com.example.speedtest.data.db.entity.ChartPoint
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
interface ChartRepository {

    fun saveGraphPoint(chartPoint: ChartPoint)

    fun getAllChartPoints(): Single<List<ChartPoint>>

}