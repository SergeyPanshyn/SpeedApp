package com.example.speedtest.presentation.chart.di

import com.example.speedtest.data.repository.ChartRepository
import com.example.speedtest.domain.chart.GetChartPointsUseCase
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import com.example.speedtest.presentation.chart.ChartPresenter
import com.example.speedtest.presentation.chart.ChartPresenterImpl
import com.example.speedtest.presentation.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
@Module
class ChartModule {

    @Provides
    @PerActivity
    fun provideChartPresenter(getChartPointsUseCase: GetChartPointsUseCase): ChartPresenter<ChartPresenter.ChartView> = ChartPresenterImpl(getChartPointsUseCase)

    @Provides
    @PerActivity
    fun provideGetChartPointsUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, graphRepository: ChartRepository)
            = GetChartPointsUseCase(observeOn, subscribeOn, graphRepository)
}