package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.WeatherEntityImpl

@Dao
internal abstract class WeatherDao {
    @Query("SELECT * FROM weather_info")
    abstract fun weatherLiveData(): LiveData<List<WeatherEntityImpl>>

    @Query("SELECT * FROM weather_info")
    abstract fun weatherInfo(): List<WeatherEntityImpl>

    @Query("DELETE FROM weather_info")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(weatherEntityImpl: WeatherEntityImpl?)
}