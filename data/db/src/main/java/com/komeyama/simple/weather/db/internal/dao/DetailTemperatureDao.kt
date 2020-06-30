package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.DetailTemperatureEntityImpl

@Dao
internal abstract class DetailTemperatureDao {
    @Query("SELECT * FROM detail_temperature")
    abstract fun detailTemperatureLiveData(): LiveData<List<DetailTemperatureEntityImpl>>

    @Query("SELECT * FROM detail_temperature")
    abstract fun detailTemperatureInfo(): List<DetailTemperatureEntityImpl>

    @Query("DELETE FROM detail_temperature")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<DetailTemperatureEntityImpl>)
}