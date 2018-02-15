package com.example.speedtest.domain.speed

import com.example.speedtest.data.db.entity.SpeedInfo
import com.example.speedtest.data.repository.SpeedRepository
import com.example.speedtest.domain.base.UseCaseSingle
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class GetSpeedInfoUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, val speedRepository: SpeedRepository): UseCaseSingle<SpeedInfo>(subscribeOn, observeOn) {
    override val useCaseSingle: Single<SpeedInfo>
        get() = speedRepository.getSpeedInfo()
}