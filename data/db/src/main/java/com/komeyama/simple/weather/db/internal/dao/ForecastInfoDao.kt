package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.ForecastInfoEntityImpl

@Dao
internal abstract class ForecastInfoDao {
    @Query("SELECT * FROM forecast_info")
    abstract fun forecastInfoLiveData(): LiveData<List<ForecastInfoEntityImpl>>

    @Query("SELECT * FROM forecast_info")
    abstract fun forecastInfo(): List<ForecastInfoEntityImpl>

    @Query("DELETE FROM forecast_info")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<ForecastInfoEntityImpl>)
}