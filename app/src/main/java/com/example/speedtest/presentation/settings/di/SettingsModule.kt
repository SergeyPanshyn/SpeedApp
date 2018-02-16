package com.example.speedtest.presentation.settings.di

import com.example.speedtest.data.repository.ChartRepository
import com.example.speedtest.data.repository.SpeedCheckManager
import com.example.speedtest.data.repository.SpeedRepository
import com.example.speedtest.domain.clear.ClearDatabaseUseCase
import com.example.speedtest.domain.location.ChangeProviderUseCase
import com.example.speedtest.domain.schedulers.ObserveOn
import com.example.speedtest.domain.schedulers.SubscribeOn
import com.example.speedtest.presentation.di.PerActivity
import com.example.speedtest.presentation.settings.SettingsPresenter
import com.example.speedtest.presentation.settings.SettingsPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
@Module
class SettingsModule {

    @Provides
    @PerActivity
    fun provideSettingsPresenter(clearDatabaseUseCase: ClearDatabaseUseCase, changeProviderUseCase: ChangeProviderUseCase): SettingsPresenter<SettingsPresenter.SettingsView>
            = SettingsPresenterImpl(clearDatabaseUseCase, changeProviderUseCase)

    @Provides
    @PerActivity
    fun provideDeleteAllChartPointsUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, chartRepository: ChartRepository, speedRepository: SpeedRepository)
            = ClearDatabaseUseCase(observeOn, subscribeOn, chartRepository, speedRepository)

    @Provides
    @PerActivity
    fun provideChangeProviderUseCase(observeOn: ObserveOn, subscribeOn: SubscribeOn, speedCheckManager: SpeedCheckManager)
            = ChangeProviderUseCase(observeOn, subscribeOn, speedCheckManager)
}