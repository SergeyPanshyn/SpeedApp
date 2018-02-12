package com.example.speedtest.presentation.settings.di

import com.example.speedtest.presentation.di.PerActivity
import com.example.speedtest.presentation.settings.SettingsFragment
import dagger.Subcomponent

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
@PerActivity
@Subcomponent(modules = arrayOf(SettingsModule::class))
interface SettingsComponent {

    fun inject(settingsFragment: SettingsFragment)

}