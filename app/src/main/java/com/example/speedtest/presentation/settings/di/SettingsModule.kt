package com.example.speedtest.presentation.settings.di

import com.example.speedtest.presentation.di.PerActivity
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
    fun provideSettingsPresenter() = SettingsPresenterImpl()

}