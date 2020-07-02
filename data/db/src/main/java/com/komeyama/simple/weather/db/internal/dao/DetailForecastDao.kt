package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.DetailForecastEntityImpl

@Dao
internal abstract class DetailForecastDao {
    @Query("SELECT * FROM detail_forecast")
    abstract fun detailForecastLiveData(): LiveData<List<DetailForecastEntityImpl>>

    @Query("SELECT * FROM detail_forecast")
    abstract fun detailForecast(): List<DetailForecastEntityImpl>

    @Query("DELETE FROM detail_forecast")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<DetailForecastEntityImpl?>?)
}