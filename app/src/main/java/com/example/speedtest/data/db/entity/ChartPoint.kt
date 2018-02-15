package com.example.speedtest.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Sergey Panshyn on 14.02.2018.
 */
@Entity
data class ChartPoint(
        @PrimaryKey(autoGenerate = true)
        val timestamp: Long,
        val speed: Int
)