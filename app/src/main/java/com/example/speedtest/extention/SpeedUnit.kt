package com.example.speedtest.extention

/**
 * Created by Sergey Panshyn on 12.02.2018.
 */
fun Float.convertMeterPerSecondToKmPerHour() = (3.6 * this).toInt()

fun Float.convertMeterPerSecondToMilesPerHour() = (2.23694 * this).toInt()

fun Float.convertKmPerHourToMeterPerSecond() = (this/3.6).toInt()

fun Float.convertMilesPerHourToMeterPerSecond() = (this/2.23694).toInt()