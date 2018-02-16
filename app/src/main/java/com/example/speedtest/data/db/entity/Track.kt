package com.example.speedtest.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Sergey Panshyn on 16.02.2018.
 */
@Entity
data class Track(
        @PrimaryKey(autoGenerate = true)
        val trackId: Int,
        val startTime: Long,
        val endTime: Long,
        val date: String,
        @Ignore val pointsList: List<ChartPoint>
)