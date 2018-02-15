package com.example.speedtest.presentation.dashboard.di

import com.example.speedtest.data.repository.ChartRepository
import com.example.speedtest.data.repository.SpeedCheckManager
import com.example.speedtest.data.repository.SpeedRepository
import com.example.speedtest.domain.chart.DeleteAllChartPointsUseCase
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import com.example.speedtest.domain.speed.GetCurrentSpeedUseCase
import com.example.speedtest.domain.speed.GetSpeedInfoUseCase
import com.example.speedtest.domain.speed.SetSpeedInfoUseCase
import com.example.speedtest.presentation.dashboard.DashboardPresenter
import com.example.speedtest.presentation.dashboard.DashboardPresenterImpl
import com.example.speedtest.presentation.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
@Module
class DashboardModule {

    @Provides
    @PerActivity
    fun provideDashboardPresenter(getCurrentSpeedUseCase: GetCurrentSpeedUseCase,
                                  getSpeedInfoUseCase: GetSpeedInfoUseCase,
                                  setSpeedInfoUseCase: SetSpeedInfoUseCase,
                                  deleteAllChartPointsUseCase: DeleteAllChartPointsUseCase): DashboardPresenter<DashboardPresenter.DashboardView>
            = DashboardPresenterImpl(getCurrentSpeedUseCase, getSpeedInfoUseCase, setSpeedInfoUseCase, deleteAllChartPointsUseCase)

    @Provides
    @PerActivity
    fun provideGetCurrentSpeedUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, speedCheckManager: SpeedCheckManager)
            = GetCurrentSpeedUseCase(subscribeOn, observeOn, speedCheckManager)

    @Provides
    @PerActivity
    fun provideGetSpeedInfoUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, speedRepository: SpeedRepository)
            = GetSpeedInfoUseCase(observeOn, subscribeOn, speedRepository)

    @Provides
    @PerActivity
    fun provideSetSpeedInfoUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, speedRepository: SpeedRepository)
            = SetSpeedInfoUseCase(observeOn, subscribeOn, speedRepository)

    @Provides
    @PerActivity
    fun provideDeleteAllChartPointsUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, chartRepository: ChartRepository)
            = DeleteAllChartPointsUseCase(observeOn, subscribeOn, chartRepository)
}