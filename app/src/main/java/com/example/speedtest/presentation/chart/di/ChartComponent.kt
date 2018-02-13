package com.example.speedtest.presentation.chart.di

import com.example.speedtest.presentation.chart.ChartFragment
import com.example.speedtest.presentation.di.PerActivity
import dagger.Subcomponent

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ChartModule::class))
interface ChartComponent {

    fun inject(chartFragment: ChartFragment)

}