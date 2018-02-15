package com.example.speedtest.presentation.chart

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.speedtest.R
import com.example.speedtest.data.db.entity.ChartPoint
import com.example.speedtest.presentation.MainActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import javax.inject.Inject


/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class ChartFragment: Fragment(), ChartPresenter.ChartView, OnChartValueSelectedListener {

    @BindView(R.id.chart_lc)
    lateinit var chartLc: LineChart

    @BindView(R.id.chart_pb)
    lateinit var chartPb: ProgressBar

    @Inject
    lateinit var chartPresenter: ChartPresenter<ChartPresenter.ChartView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = layoutInflater.inflate(R.layout.chart_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()
        testInitGraph()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).chartComponent?.inject(this)
        chartPresenter.setView(this)
        chartPresenter.getGraphPoints()

    }

    override fun showGraphPoints(listValues: List<ChartPoint>) {
        setData(listValues)
    }

    private fun setData(listValues: List<ChartPoint>) {

        if (!listValues.isEmpty()) {

            val values = ArrayList<Entry>()

            for (i in 0 until listValues.size) {
                values.add(Entry(i.toFloat(), listValues[i].speed.toFloat(), 0))
            }
            val set: LineDataSet
            set = createSet(values)

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set)

            val data = LineData(dataSets)

            chartLc.data = data
            chartLc.data.isHighlightEnabled = true
            chartLc.setVisibleXRangeMaximum(50f)
            chartLc.moveViewToX(data.entryCount.toFloat())

        }

        chartPb.visibility = View.GONE
        if (chartLc.data.entryCount > 0) {
            createMarker()
        }

    }

    private fun createMarker() {
        chartLc.setDrawMarkers(true)
        val mv = MyMarkerView(context!!, R.layout.custom_marker_view)
        mv.chartView = chartLc
        chartLc.marker = mv
    }

    private fun createSet(values: ArrayList<Entry>?): LineDataSet {
        val set = LineDataSet(values, "")
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.color = resources.getColor(R.color.red)
        set.lineWidth = 1f
        set.fillAlpha = 65
        set.fillColor = resources.getColor(R.color.red)
        set.highLightColor = Color.rgb(244, 117, 117)
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.setDrawHighlightIndicators(true)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        return set
    }

    private fun testInitGraph() {

        chartLc.setOnChartValueSelectedListener(this)

        chartLc.getDescription().setEnabled(false)

        chartLc.setTouchEnabled(true)

        chartLc.dragDecelerationFrictionCoef = 0.9f

        chartLc.isDragEnabled = true
        chartLc.setScaleEnabled(true)
        chartLc.setDrawGridBackground(false)
        chartLc.isHighlightPerDragEnabled = true

        chartLc.setPinchZoom(true)

        chartLc.setBackgroundColor(Color.TRANSPARENT)

        val data = LineData()
        data.setValueTextColor(Color.WHITE)

        chartLc.data = data

        val l = chartLc.legend

        l.form = Legend.LegendForm.NONE
        l.textColor = Color.WHITE

        val xAxisFormatter = AxisValueFormatter(chartLc)

        val xAxis = chartLc.xAxis
        xAxis.enableGridDashedLine(10f, 10f, 0f)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor
        xAxis.textSize = 10f
        xAxis.textColor = Color.GRAY
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.setCenterAxisLabels(true)
        xAxis.granularity = 5f
        xAxis.valueFormatter = xAxisFormatter

        val leftAxis = chartLc.axisLeft
        leftAxis.setDrawAxisLine(false)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawLabels(true)
        leftAxis.textColor = Color.WHITE
        leftAxis.axisMinimum = 0f


        val rightAxis = chartLc.axisRight
        rightAxis.isEnabled = false
    }

    override fun onNothingSelected() {

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }
}