package com.example.speedtest.extention

import android.content.Context
import android.preference.PreferenceManager
import com.example.speedtest.R

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
fun Context.saveDistanceUnit(distanceUnit: String) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    prefs.edit().putString(getString(R.string.distance_unit), distanceUnit).apply()
}

fun Context.getDistanceUnit(): String {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    return prefs.getString(getString(R.string.distance_unit), "km")
}

fun Context.saveProvider(provider: String) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    prefs.edit().putString(getString(R.string.provider), provider).apply()
}

fun Context.getProvider(): String {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    return prefs.getString(getString(R.string.provider), "gps")
}
