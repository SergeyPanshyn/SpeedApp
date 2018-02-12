package com.example.speedtest.presentation.dashboard.di

import com.example.speedtest.presentation.di.PerActivity
import com.example.speedtest.presentation.dashboard.DashboardFragment
import dagger.Subcomponent

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
@PerActivity
@Subcomponent(modules = arrayOf(DashboardModule::class))
interface DashboardComponent {

    fun inject(dashboardFragment: DashboardFragment)

}