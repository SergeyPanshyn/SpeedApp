package com.example.speedtest.data.repository

import com.example.speedtest.data.db.SpeedDatabase
import com.example.speedtest.data.db.entity.ChartPoint
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class ChartRepositoryImpl(private val database: SpeedDatabase): ChartRepository {
    override fun saveGraphPoint(chartPoint: ChartPoint) {
//        val realm = Realm.getDefaultInstance()
//
//        graphPointModel.assignPrimaryKey()
//        realm.use {
//            it.executeTransaction {
//                it.copyToRealm(graphPointModel)
//            }
//        }
        database.getChartPointDao().insertChartPoint(chartPoint)
    }

    override fun getAllChartPoints(): Single<List<ChartPoint>> {
//        val realm = Realm.getDefaultInstance()
//
//        realm.use {
//            return Single.just(realm.copyFromRealm(realm.where(GraphPointModel::class.java).findAll()))
//        }
        return database.getChartPointDao().getAllChartPoints()
    }

}