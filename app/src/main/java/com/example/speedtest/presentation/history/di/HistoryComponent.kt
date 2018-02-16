package com.example.speedtest.presentation.history.di

import com.example.speedtest.presentation.di.PerActivity
import com.example.speedtest.presentation.history.HistoryFragment
import dagger.Subcomponent

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
@PerActivity
@Subcomponent(modules = arrayOf(HistoryModule::class))
interface HistoryComponent {

    fun inject(historyFragment: HistoryFragment)

}