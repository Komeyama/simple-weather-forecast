package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.PinpointLocationEntityImpl

@Dao
internal abstract class PinpointLocationDao {
    @Query("SELECT * FROM pinpoint_location")
    abstract fun pinpointLocationLiveData(): LiveData<List<PinpointLocationEntityImpl>>

    @Query("SELECT * FROM pinpoint_location")
    abstract fun pinpointLocations(): List<PinpointLocationEntityImpl>

    @Query("DELETE FROM pinpoint_location")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<PinpointLocationEntityImpl>)
}