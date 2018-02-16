package com.example.speedtest.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.speedtest.R
import com.example.speedtest.SpeedApp
import com.example.speedtest.SpeedService
import com.example.speedtest.presentation.chart.ChartFragment
import com.example.speedtest.presentation.chart.di.ChartModule
import com.example.speedtest.presentation.dashboard.DashboardFragment
import com.example.speedtest.presentation.dashboard.di.DashboardModule
import com.example.speedtest.presentation.history.di.HistoryModule
import com.example.speedtest.presentation.settings.SettingsFragment
import com.example.speedtest.presentation.settings.di.SettingsModule

class MainActivity : AppCompatActivity() {

    @BindView(R.id.bottom_navigation_view)
    lateinit var bottomNavigationView: BottomNavigationView

    companion object {
        val LOCATION_REQUEST = 123
        val DASHBOARD_FRAGMENT_TAG = "DashboardFragmentTag"
        val SETTINGS_FRAGMENT_TAG = "SettingsFragmentTag"
        val CHART_FRAGMENT_TAG = "ChartFragmentTag"
        val MAP_FRAGMENT_TAG = "MapFragmentTag"
        val HISTORY_FRAGMENT_TAG = "HistoryFragmentTag"
    }

    val dashboardComponent by lazy { SpeedApp.appComponent?.provideDashboardComponent(DashboardModule()) }
    val settingsComponent by lazy { SpeedApp.appComponent?.provideSettingsComponent(SettingsModule()) }
    val chartComponent by lazy { SpeedApp.appComponent?.provideChartComponent(ChartModule()) }
    val historyComponent by lazy { SpeedApp.appComponent?.provideHistoryComponent(HistoryModule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        checkLocationPermission()

        if (savedInstanceState == null) {
            setupFragment()
        }

        setupBottomNavigationListener()
    }

    private fun setupBottomNavigationListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId) {
                R.id.action_history -> {
                    replaceFragment(ChartFragment(), CHART_FRAGMENT_TAG)
                    true
                }
                R.id.action_live -> {
                    replaceFragment(DashboardFragment(), DASHBOARD_FRAGMENT_TAG)
                    true
                }
                R.id.action_settings -> {
                    replaceFragment(SettingsFragment(), SETTINGS_FRAGMENT_TAG)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setupFragment() {
        bottomNavigationView.menu.getItem(1).isChecked = true
        replaceFragment(DashboardFragment(), DASHBOARD_FRAGMENT_TAG)
    }

    private fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        if (supportFragmentManager.findFragmentByTag(tag) != null)
            return

        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.graph_container_fl, fragment, tag)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
    }

    private fun runSpeedService() {
        if (!SpeedService.IS_RUNNING) {
            val speedServiceIntent = Intent(this, SpeedService::class.java)
            startService(speedServiceIntent)
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, Array(1) { Manifest.permission.ACCESS_FINE_LOCATION }, LOCATION_REQUEST)
            } else {
                ActivityCompat.requestPermissions(this, Array(1) { Manifest.permission.ACCESS_FINE_LOCATION }, LOCATION_REQUEST)
            }
        } else {
            runSpeedService()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Super!", Toast.LENGTH_SHORT).show();
                runSpeedService()
            } else {
                Toast.makeText(applicationContext, "We need location permission to continue working.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
