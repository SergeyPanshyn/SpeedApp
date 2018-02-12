package com.example.speedtest.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.ButterKnife
import com.example.speedtest.R
import com.example.speedtest.SpeedApp
import com.example.speedtest.SpeedService
import com.example.speedtest.presentation.dashboard.DashboardFragment
import com.example.speedtest.presentation.dashboard.di.DashboardModule
import com.example.speedtest.presentation.settings.di.SettingsModule

class MainActivity : AppCompatActivity() {

    companion object {
        val LOCATION_REQUEST = 123
        val DASHBOARD_FRAGMENT_TAG = "DashboardFragmentTag"
        val SETTINGS_FRAGMENT_TAG = "SettingsFragmentTag"
    }

    val dashboardComponent by lazy { SpeedApp.appComponent?.provideDashboardComponent(DashboardModule()) }
    val settingsComponent by lazy { SpeedApp.appComponent?.provideSettingsComponent(SettingsModule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        checkLocationPermission()

        if (savedInstanceState == null) {
            setupFragment()
        }
    }

    private fun setupFragment() {
        replaceFragment(DashboardFragment(), DASHBOARD_FRAGMENT_TAG)
    }

    private fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
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
