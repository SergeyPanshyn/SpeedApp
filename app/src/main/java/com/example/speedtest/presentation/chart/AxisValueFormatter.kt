package com.example.speedtest.presentation.chart

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

open class AxisValueFormatter(val graph: LineChart): IAxisValueFormatter {

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return getDateCurrentTimeZone(value.toLong())
    }

    private fun getDateCurrentTimeZone(timestamp: Long): String {
        try {
            val date = Date(timestamp*1000)
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getTimeZone("UTC")
            calendar.timeInMillis = timestamp * 1000
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("HH:mm")
            val currentTimeZone = calendar.time
            return sdf.format(currentTimeZone)
        } catch (e: Exception) {
        }
        return ""
    }

}
