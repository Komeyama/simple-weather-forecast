package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.PinpointLocationOfCopyEntityImpl

@Dao
internal abstract class PinpointLocationOfCopyDao {
    @Query("SELECT * FROM pinpoint_location_copy")
    abstract fun pinpointLocationOfCopyLiveData(): LiveData<List<PinpointLocationOfCopyEntityImpl>>

    @Query("SELECT * FROM pinpoint_location_copy")
    abstract fun pinpointLocationsOfCopy(): List<PinpointLocationOfCopyEntityImpl>

    @Query("DELETE FROM pinpoint_location_copy")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<PinpointLocationOfCopyEntityImpl>)
}