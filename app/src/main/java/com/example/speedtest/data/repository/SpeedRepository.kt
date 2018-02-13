package com.example.speedtest.data.repository

import com.example.speedtest.data.models.SpeedInfoModel
import rx.Completable
import rx.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
interface SpeedRepository {

    fun setSpeedInfo(speedInfoModel: SpeedInfoModel): Completable

    fun getSpeedInfo(): Single<SpeedInfoModel>

}