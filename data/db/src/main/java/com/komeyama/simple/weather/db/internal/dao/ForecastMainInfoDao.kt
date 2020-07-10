package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.ForecastMainInfoEntityImpl

@Dao
internal abstract class ForecastMainInfoDao {
    @Query("SELECT * FROM forecast_info")
    abstract fun forecastMainInfoLiveData(): LiveData<List<ForecastMainInfoEntityImpl>>

    @Query("SELECT * FROM forecast_info")
    abstract fun forecastMainInfo(): List<ForecastMainInfoEntityImpl>

    @Query("DELETE FROM forecast_info")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(forecastMainInfoEntityImpl: ForecastMainInfoEntityImpl?)
}