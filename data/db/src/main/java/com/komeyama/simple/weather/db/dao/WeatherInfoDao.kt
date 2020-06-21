package com.komeyama.simple.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.entity.WeatherInfoEntityImpl

@Dao
abstract class WeatherInfoDao {
    @Query("SELECT * FROM weather_info")
    abstract fun weatherInfoLiveData(): LiveData<List<WeatherInfoEntityImpl>>

    @Query("SELECT * FROM weather_info")
    abstract fun weatherInfo(): List<WeatherInfoEntityImpl>

    @Query("DELETE FROM weather_info")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<WeatherInfoEntityImpl>)
}