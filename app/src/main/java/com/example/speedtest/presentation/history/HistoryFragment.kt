package com.example.speedtest.presentation.history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.speedtest.R
import com.example.speedtest.extention.setRegularFont
import com.example.speedtest.presentation.MainActivity
import com.example.speedtest.presentation.history.adapter.HistoryAdapter
import javax.inject.Inject

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
class HistoryFragment: Fragment(), HistoryPresenter.HistoryView {

    @BindView(R.id.history_title)
    lateinit var historyTitleTv: TextView

    @BindView(R.id.history_rv)
    lateinit var historyRv: RecyclerView

    @Inject
    lateinit var historyPresenter: HistoryPresenter<HistoryPresenter.HistoryView>

    private val historyFragmentListener: HistoryFragment.HistoryFragmentListener by lazy { context as HistoryFragment.HistoryFragmentListener }

    interface HistoryFragmentListener {

        fun onItemSelected(trackId: Int)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.history_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        setTypeface()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).historyComponent?.inject(this)
        historyPresenter.setView(this)
        historyPresenter.getTrackList()
    }

    override fun showTrackList(data: List<String>) {
        initRecyclerView(data)
    }

    private fun initRecyclerView(data: List<String>) {
        val adapter = HistoryAdapter(context!!,
                data,
                View.OnClickListener {
                    TODO("cahnge '0' to real track number")
                    historyFragmentListener.onItemSelected(0)
                })
        historyRv.adapter = adapter
        historyRv.layoutManager = LinearLayoutManager(context)
    }

    private fun setTypeface() {
        historyTitleTv.setRegularFont(context!!)
    }
}