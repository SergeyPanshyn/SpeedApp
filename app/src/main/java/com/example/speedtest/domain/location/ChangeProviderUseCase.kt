package com.example.speedtest.domain.location

import com.example.speedtest.data.repository.SpeedCheckManager
import com.example.speedtest.domain.base.UseCaseCompletable
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.Completable

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
class ChangeProviderUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, val speedCheckManager: SpeedCheckManager): UseCaseCompletable(subscribeOn, observeOn) {
    override val useCaseCompletable: Completable
        get() = speedCheckManager.changeProvider()
}