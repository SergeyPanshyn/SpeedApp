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
import com.example.speedtest.data.db.entity.SpeedInfo
import com.example.speedtest.extention.setBoldFont
import com.example.speedtest.extention.setLightFont
import com.example.speedtest.extention.setRegularFont
import com.example.speedtest.presentation.MainActivity
import java.text.DecimalFormat
import javax.inject.Inject

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */

class DashboardFragment: Fragment(), DashboardPresenter.DashboardView {

    @BindView(R.id.speet_circle_iv)
    lateinit var speetCircleIv: ImageView

    @BindView(R.id.speed_title_tv)
    lateinit var speedTitleTv: TextView

    @BindView(R.id.speed_tv)
    lateinit var speedTv: TextView

    @BindView(R.id.speed_unit_tv)
    lateinit var speedUnitTv: TextView

    @BindView(R.id.max_speed_title_tv)
    lateinit var maxSpeedTitleTv: TextView

    @BindView(R.id.max_speed_tv)
    lateinit var maxSpeedTv: TextView

    @BindView(R.id.max_speed_unit_tv)
    lateinit var maxSpeedUnitTv: TextView

    @BindView(R.id.total_distance_title_tv)
    lateinit var totalDistanceTitleTv: TextView

    @BindView(R.id.total_distance_tv)
    lateinit var totalDistanceTv: TextView

    @BindView(R.id.total_distance_unit_tv)
    lateinit var totalDistanceUnitTv: TextView

    @Inject
    lateinit var dashboardPresenter: DashboardPresenter<DashboardPresenter.DashboardView>

    private val dashboardListener: DashboardFragment.DashboardListener by lazy { context as DashboardFragment.DashboardListener }

    interface DashboardListener {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.dashboard_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        setTypefaces()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).dashboardComponent?.inject(this)
        dashboardPresenter.setView(this)
        dashboardPresenter.subscribeToCurrentLocation()
        dashboardPresenter.getSpeedInfoModel()
        setLoadingState()
    }

    private fun setLoadingState() {
        speedTv.text = "0"
        maxSpeedTv.text = "-/- "
        totalDistanceTv.text = "-/- "
    }

    override fun showSpeed(speedInfo: SpeedInfo) {
        speedTv.text = speedInfo.currentSpeed.toString()
        maxSpeedTv.text = speedInfo.maxSpeed.toString()
        setTotalDistance(speedInfo.totalDistance)
    }

    private fun setTotalDistance(distance: Int) {
        if (distance < 1000) {
            totalDistanceTv.text = distance.toString()
            totalDistanceUnitTv.text = "m"
        } else {
            totalDistanceTv.text = (DecimalFormat("##.##").format(distance.toDouble()/1000))
            totalDistanceUnitTv.text = "km"
        }
    }

    override fun showSpeedInfo(speedInfoModel: SpeedInfo) {
        maxSpeedTv.text = speedInfoModel.maxSpeed.toString()
        setTotalDistance(speedInfoModel.totalDistance)
    }

    private fun setTypefaces() {
        speedTitleTv.setLightFont(context!!)
        speedTv.setBoldFont(context!!)
        speedUnitTv.setLightFont(context!!)
        maxSpeedTitleTv.setRegularFont(context!!)
        maxSpeedTv.setBoldFont(context!!)
        maxSpeedUnitTv.setLightFont(context!!)
        totalDistanceTitleTv.setRegularFont(context!!)
        totalDistanceTv.setBoldFont(context!!)
        totalDistanceUnitTv.setLightFont(context!!)
    }
}
