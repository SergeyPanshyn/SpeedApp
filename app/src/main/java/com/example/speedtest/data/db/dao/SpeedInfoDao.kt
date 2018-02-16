package com.example.speedtest.data.db.dao

import android.arch.persistence.room.*
import com.example.speedtest.data.db.entity.SpeedInfo
import io.reactivex.Single

/**
 * Created by Sergey Panshyn on 14.02.2018.
 */
@Dao
interface SpeedInfoDao {

    @Query("SELECT * FROM speedInfo")
    fun getSpeedInfo(): Single<SpeedInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeedInfo(speedInfo: SpeedInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSpeedInfo(speedInfo: SpeedInfo)

    @Query("DELETE FROM speedinfo")
    fun clearSpeedInfo()
}