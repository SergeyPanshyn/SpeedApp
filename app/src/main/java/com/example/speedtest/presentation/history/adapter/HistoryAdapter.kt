package com.example.speedtest.presentation.history.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.speedtest.R

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
class HistoryAdapter(val context: Context, val data: List<String>, val onItemClickListener: View.OnClickListener): RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onBindViewHolder(holder: HistoryViewHolder?, position: Int) {
        val current = data[position]
        TODO("Implement when model is ready.")
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.history_recycler_item, parent, false)
        return HistoryViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}