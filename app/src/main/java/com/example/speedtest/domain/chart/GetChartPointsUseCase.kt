package com.example.speedtest.domain.chart

import com.example.speedtest.data.db.entity.ChartPoint
import com.example.speedtest.data.repository.ChartRepository
import com.example.speedtest.domain.base.UseCaseSingle
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class GetChartPointsUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, val graphRepository: ChartRepository): UseCaseSingle<List<ChartPoint>>(subscribeOn, observeOn) {
    override val useCaseSingle: Single<List<ChartPoint>>
        get() = graphRepository.getAllChartPoints()
}