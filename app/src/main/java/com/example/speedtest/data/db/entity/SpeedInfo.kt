package com.example.speedtest.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Sergey Panshyn on 14.02.2018.
 */
@Entity
class SpeedInfo (
        @PrimaryKey
        val id: Int = 1,
        var maxSpeed: Int,
        var totalDistance: Int,
        var currentSpeed: Int

)