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
        database.getSpeedInfoDao().insertSpeedInfo(speedInfo)
        return Completable.complete()
    }

    override fun getSpeedInfo(): Single<SpeedInfo> {
        return database.getSpeedInfoDao().getSpeedInfo()
    }

    override fun clearSpeedInfo(): Completable {
        database.getSpeedInfoDao().clearSpeedInfo()
        return Completable.complete()
    }

}