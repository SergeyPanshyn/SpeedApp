package com.example.speedtest.data.db.dao

import android.arch.persistence.room.*
import com.example.speedtest.data.db.entity.ChartPoint
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 14.02.2018.
 */
@Dao
interface ChartPointDao {

    @Query("SELECT * FROM chartpoint")
    fun getAllChartPoints(): Single<List<ChartPoint>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChartPoint(chartPoint: ChartPoint)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChartPoint(chartPoint: ChartPoint)

    @Delete
    fun deleteChartPoint(chartPoint: ChartPoint)
}