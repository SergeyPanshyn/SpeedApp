package com.example.speedtest.data.repository

import com.example.speedtest.data.db.SpeedDatabase
import com.example.speedtest.data.db.entity.SpeedInfo
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class SpeedRepositoryImpl(private val database: SpeedDatabase): SpeedRepository {
    override fun setSpeedInfo(speedInfo: SpeedInfo): Completable {
//        val realm = Realm.getDefaultInstance()
//
//        realm.use {
//            it.executeTransaction {
//                it.copyToRealmOrUpdate(speedInfoModel)
//            }
//        }
        database.getSpeedInfoDao().insertSpeedInfo(speedInfo)
        return Completable.complete()
    }

    override fun getSpeedInfo(): Single<SpeedInfo> {
//        val realm = Realm.getDefaultInstance()
//        val speedInfoModel = realm.where(SpeedInfoModel::class.java).findFirst() ?: return Single.just(SpeedInfoModel(0, 0, 0))
//
//        realm.use {
//            return Single.just(speedInfoModel)
//        }
        return database.getSpeedInfoDao().getSpeedInfo()
    }

}