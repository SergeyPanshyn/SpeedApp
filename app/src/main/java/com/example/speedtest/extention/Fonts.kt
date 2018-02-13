package com.example.speedtest.extention

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import com.example.speedtest.R

fun TextView.setLightFont(context: Context) {
    this.typeface = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fontLight))
}

fun TextView.setMediumFont(context: Context) {
    this.typeface = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fontMedium))
}

fun TextView.setRegularFont(context: Context) {
    this.typeface = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fontRegular))
}

fun TextView.setSemiboldFont(context: Context) {
    this.typeface = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fontSemibold))
}

fun TextView.setBoldFont(context: Context) {
    this.typeface = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fontBold))
}
