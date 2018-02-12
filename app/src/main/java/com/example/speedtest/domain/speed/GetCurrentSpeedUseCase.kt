package com.example.speedtest.domain.speed

import com.example.speedtest.data.repository.SpeedCheckManager
import com.example.speedtest.domain.base.UseCaseStream
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import rx.Observable

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class GetCurrentSpeedUseCase(subscibeOn: SubscribeOn, observeOn: ObserveOn, val speedCheckManager: SpeedCheckManager): UseCaseStream<Float>(subscibeOn, observeOn) {
    override val useCaseObservable: Observable<Float>
        get() = speedCheckManager.listenForSpeed()
}