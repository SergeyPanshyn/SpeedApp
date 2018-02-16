package com.example.speedtest.domain.clear

import com.example.speedtest.data.repository.ChartRepository
import com.example.speedtest.data.repository.SpeedRepository
import com.example.speedtest.domain.base.UseCaseCompletable
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.Completable

/**
 * Created by Sergey Panshyn on 15.02.2018.
 */
class ClearDatabaseUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, val chartRepository: ChartRepository, val speedRepository: SpeedRepository): UseCaseCompletable(subscribeOn, observeOn) {
    override val useCaseCompletable: Completable
        get() = chartRepository.deleteAllChartPoints().andThen(speedRepository.clearSpeedInfo())
}