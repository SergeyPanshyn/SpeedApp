package com.example.speedtest.presentation.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.speedtest.R

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
class MapFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.map_fragment, container, false)

        ButterKnife.bind(this, fragment)

        return fragment
    }
}