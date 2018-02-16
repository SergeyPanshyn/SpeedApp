package com.example.speedtest.data.repository

import com.example.speedtest.data.db.entity.SpeedInfo
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
interface SpeedRepository {

    fun setSpeedInfo(speedInfo: SpeedInfo): Completable

    fun getSpeedInfo(): Single<SpeedInfo>

    fun clearSpeedInfo(): Completable

}