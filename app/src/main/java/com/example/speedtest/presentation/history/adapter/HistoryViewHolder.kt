package com.example.speedtest.presentation.history.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.speedtest.R

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
class HistoryViewHolder(itemView: View, onItemClickListener: View.OnClickListener): RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.track_number)
    lateinit var trackNumber: TextView

    @BindView(R.id.track_date)
    lateinit var trackDate: TextView

    @BindView(R.id.track_time)
    lateinit var trackTime: TextView

    @BindView(R.id.root_card_view)
    lateinit var rootCardView: CardView

    init {
        ButterKnife.bind(this, itemView)

        rootCardView.setOnClickListener {
            it.tag = adapterPosition
            onItemClickListener.onClick(it)
        }
    }

}