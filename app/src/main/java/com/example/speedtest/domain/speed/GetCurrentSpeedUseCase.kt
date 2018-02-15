package com.example.speedtest.domain.speed

import com.example.speedtest.data.db.entity.SpeedInfo
import com.example.speedtest.data.repository.SpeedCheckManager
import com.example.speedtest.domain.base.UseCaseStream
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.Observable

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class GetCurrentSpeedUseCase(subscibeOn: SubscribeOn, observeOn: ObserveOn, val speedCheckManager: SpeedCheckManager): UseCaseStream<SpeedInfo>(subscibeOn, observeOn) {
    override val useCaseObservable: Observable<SpeedInfo>
        get() = speedCheckManager.listenForSpeed()
}