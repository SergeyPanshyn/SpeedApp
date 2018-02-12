package com.example.speedtest.presentation.dashboard.di

import com.example.speedtest.presentation.di.PerActivity
import com.example.speedtest.presentation.dashboard.DashboardPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
@Module
class DashboardModule {

    @Provides
    @PerActivity
    fun provideDashboardPresenter() = DashboardPresenterImpl()

}