package com.example.speedtest.presentation.di

import com.example.speedtest.SpeedApp
import com.example.speedtest.SpeedService
import com.example.speedtest.data.di.DataModule
import com.example.speedtest.presentation.chart.di.ChartComponent
import com.example.speedtest.presentation.chart.di.ChartModule
import com.example.speedtest.presentation.dashboard.di.DashboardComponent
import com.example.speedtest.presentation.dashboard.di.DashboardModule
import com.example.speedtest.presentation.history.di.HistoryComponent
import com.example.speedtest.presentation.history.di.HistoryModule
import com.example.speedtest.presentation.settings.di.SettingsComponent
import com.example.speedtest.presentation.settings.di.SettingsModule
import dagger.Component
import javax.inject.Singleton


@Component(modules = arrayOf(AppModule::class, DataModule::class))
@Singleton
interface AppComponent {

    fun provideDashboardComponent(dashboardModule: DashboardModule): DashboardComponent

    fun provideSettingsComponent(settingModule: SettingsModule): SettingsComponent

    fun provideChartComponent(chartModule: ChartModule): ChartComponent

    fun provideHistoryComponent(historyModule: HistoryModule): HistoryComponent

    fun inject(speedService: SpeedService)

    fun inject(speedApp: SpeedApp)

}