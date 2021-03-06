package com.komeyama.simple.weather.db.internal.dao

import androidx.room.*
import com.komeyama.simple.weather.db.internal.entity.ForecastInfoImpl
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class ForecastInfoDao {
    @Transaction
    @Query("SELECT * FROM forecast_info")
    abstract fun forecastInfo(): List<ForecastInfoImpl>

    @Transaction
    @Query("SELECT * FROM forecast_info")
    abstract fun forecastInfoFlow(): Flow<List<ForecastInfoImpl>>
}