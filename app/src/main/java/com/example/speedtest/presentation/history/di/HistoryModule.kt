package com.example.speedtest.presentation.history.di

import com.example.speedtest.presentation.di.PerActivity
import com.example.speedtest.presentation.history.HistoryPresenter
import com.example.speedtest.presentation.history.HistoryPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
@Module
class HistoryModule {

    @Provides
    @PerActivity
    fun provideHistoryPresenter(): HistoryPresenter<HistoryPresenter.HistoryView> = HistoryPresenterImpl()

}