package com.example.speedtest.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.speedtest.data.db.dao.ChartPointDao
import com.example.speedtest.data.db.dao.SpeedInfoDao
import com.example.speedtest.data.db.entity.ChartPoint
import com.example.speedtest.data.db.entity.SpeedInfo

/**
 * Created by Sergey Panshyn on 14.02.2018.
 */
@Database(entities = arrayOf(ChartPoint::class, SpeedInfo::class), version = 1)
abstract class SpeedDatabase : RoomDatabase() {
    abstract fun getChartPointDao(): ChartPointDao

    abstract fun getSpeedInfoDao(): SpeedInfoDao
}