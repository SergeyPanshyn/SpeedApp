package com.example.speedtest.data.repository

import com.example.speedtest.data.db.SpeedDatabase
import com.example.speedtest.data.db.entity.ChartPoint
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class ChartRepositoryImpl(private val database: SpeedDatabase): ChartRepository {
    override fun saveGraphPoint(chartPoint: ChartPoint) {
        database.getChartPointDao().insertChartPoint(chartPoint)
    }

    override fun getAllChartPoints(): Single<List<ChartPoint>> {
        return database.getChartPointDao().getAllChartPoints()
    }

    override fun deleteAllChartPoints(): Completable {
        database.getChartPointDao().deleteTable()
        return Completable.complete()
    }
}