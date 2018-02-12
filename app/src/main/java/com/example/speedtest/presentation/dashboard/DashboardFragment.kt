package com.example.speedtest.presentation.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.speedtest.R
import com.example.speedtest.presentation.MainActivity
import javax.inject.Inject

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */

class DashboardFragment: Fragment(), DashboardPresenter.DashboardView {

    @BindView(R.id.speet_circle_iv)
    lateinit var speetCircleIv: ImageView

    @BindView(R.id.speed_tv)
    lateinit var speedTv: TextView

    @BindView(R.id.unit_tv)
    lateinit var unitTv: TextView

    @Inject
    lateinit var dashboardPresenter: DashboardPresenter<DashboardPresenter.DashboardView>

    private val dashboardListener: DashboardFragment.DashboardListener by lazy { context as DashboardFragment.DashboardListener }

    interface DashboardListener {

        fun onSettingsButtonClick()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.dashboard_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).dashboardComponent?.inject(this)
        dashboardPresenter.setView(this)
        dashboardPresenter.subscribeToCurrentSpeed()
    }

    override fun showSpeed(speed: Int) {
        speedTv.text = speed.toString()
    }
}
