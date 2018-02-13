package com.example.speedtest.domain.speed

import com.example.speedtest.data.models.SpeedInfoModel
import com.example.speedtest.data.repository.SpeedRepository
import com.example.speedtest.domain.base.UseCaseCompletable
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import rx.Completable

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class SetSpeedInfoUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, val speedRepository: SpeedRepository): UseCaseCompletable(subscribeOn, observeOn) {
    override val useCaseCompletable: Completable
        get() = speedRepository.setSpeedInfo(speedInfoModel!!)

    var speedInfoModel: SpeedInfoModel? = null

}