package com.example.speedtest.domain.speed

import com.example.speedtest.data.models.SpeedInfoModel
import com.example.speedtest.data.repository.SpeedRepository
import com.example.speedtest.domain.base.UseCaseSingle
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import rx.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class GetSpeedInfoUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, val speedRepository: SpeedRepository): UseCaseSingle<SpeedInfoModel>(subscribeOn, observeOn) {
    override val useCaseSingle: Single<SpeedInfoModel>
        get() = speedRepository.getSpeedInfo()
}