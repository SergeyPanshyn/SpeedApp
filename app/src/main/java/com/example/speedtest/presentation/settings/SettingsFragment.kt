package com.example.speedtest.presentation.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.speedtest.R
import com.example.speedtest.extention.*
import com.example.speedtest.presentation.MainActivity
import javax.inject.Inject


/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
class SettingsFragment: Fragment(), SettingsPresenter.SettingsView {

    @BindView(R.id.settings_distance_radio_group)
    lateinit var settingsDistanceRadioGroup: RadioGroup

    @BindView(R.id.settings_provider_radio_group)
    lateinit var settingsProviderRadioGroup: RadioGroup

    @BindView(R.id.settings_title)
    lateinit var settingsTitle: TextView

    @BindView(R.id.settings_distance_tv)
    lateinit var settingsDistanceTv: TextView

    @BindView(R.id.settings_provider_tv)
    lateinit var settingsProviderTv: TextView

    @BindView(R.id.settings_reset_tv)
    lateinit var settingsResetTv: TextView

    @Inject
    lateinit var settingsPresenter: SettingsPresenter<SettingsPresenter.SettingsView>

    private val settingsListener: SettingsListener by lazy { context as SettingsFragment.SettingsListener }

    interface SettingsListener {

        fun onProviderChanged()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.settings_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        initSettings()

        setTypefaces()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).settingsComponent?.inject(this)
        settingsPresenter.setView(this)
    }

    private fun initSettings() {
        initDistanceRadioGroup()

        initProviderRadioGroup()

        settingsResetTv.setOnClickListener { settingsPresenter.clearDatabase() }
    }

    private fun initDistanceRadioGroup() {
        when (context?.getDistanceUnit()) {
            context?.getString(R.string.distance_unit_km) -> { settingsDistanceRadioGroup.check(R.id.settings_distance_radio_km) }
            context?.getString(R.string.distance_unit_miles) -> { settingsDistanceRadioGroup.check(R.id.settings_distance_radio_miles) }
        }
        settingsDistanceRadioGroup.setOnCheckedChangeListener { _, i ->
            when(i) {
                R.id.settings_distance_radio_miles -> { context?.saveDistanceUnit(getString(R.string.distance_unit_miles)) }
                R.id.settings_distance_radio_km -> { context?.saveDistanceUnit(getString(R.string.distance_unit_km)) }
            }
        }
    }

    private fun initProviderRadioGroup() {
        when (context?.getProvider()) {
            context?.getString(R.string.provider_gps) -> { settingsProviderRadioGroup.check(R.id.settings_provider_radio_gps) }
            context?.getString(R.string.provider_network) -> { settingsProviderRadioGroup.check(R.id.settings_provider_radio_network) }
        }
        settingsProviderRadioGroup.setOnCheckedChangeListener { _, i ->
            when(i) {
                R.id.settings_provider_radio_gps -> {
                    context?.saveProvider(getString(R.string.provider_gps))
                    settingsPresenter.changeProvider()
                }
                R.id.settings_provider_radio_network -> {
                    context?.saveProvider(getString(R.string.provider_network))
                    settingsPresenter.changeProvider()
                }
            }
        }
    }

    private fun setTypefaces() {
        settingsTitle.setRegularFont(context!!)
        settingsDistanceTv.setRegularFont(context!!)
        settingsProviderTv.setRegularFont(context!!)
        settingsResetTv.setRegularFont(context!!)
    }
}