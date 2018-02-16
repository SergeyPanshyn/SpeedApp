package com.example.speedtest.presentation.history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.speedtest.R
import com.example.speedtest.presentation.MainActivity
import javax.inject.Inject

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
class HistoryFragment: Fragment(), HistoryPresenter.HistoryView {

    @Inject
    lateinit var historyPresenter: HistoryPresenter<HistoryPresenter.HistoryView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.history_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).historyComponent?.inject(this)
        historyPresenter.setView(this)
    }
}