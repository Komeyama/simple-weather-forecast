package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.TemperatureEntityImpl

@Dao
internal abstract class TemperatureDao {
    @Query("SELECT * FROM temperature")
    abstract fun temperatureLiveData(): LiveData<List<TemperatureEntityImpl>>

    @Query("SELECT * FROM temperature")
    abstract fun temperatureInfo(): List<TemperatureEntityImpl>

    @Query("DELETE FROM temperature")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<TemperatureEntityImpl>)
}