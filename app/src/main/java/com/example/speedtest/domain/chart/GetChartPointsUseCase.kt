package com.example.speedtest.domain.chart

import com.example.speedtest.data.models.GraphPointModel
import com.example.speedtest.data.repository.GraphRepository
import com.example.speedtest.domain.base.UseCaseSingle
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import rx.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class GetChartPointsUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, val graphRepository: GraphRepository): UseCaseSingle<List<GraphPointModel>>(subscribeOn, observeOn) {
    override val useCaseSingle: Single<List<GraphPointModel>>
        get() = graphRepository.getGraphPoints()
}