package com.komeyama.simple.weather.db.internal.dao.old

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.komeyama.simple.weather.db.internal.entity.old.PinpointLocationEntityImpl
//import com.komeyama.simple.weather.db.internal.entity.old.PinpointLocationOfCopyEntityImpl

//@Dao
//internal abstract class PinpointLocationDao {
//    @Query("SELECT * FROM pinpoint_location")
//    abstract fun pinpointLocationLiveData(): LiveData<List<PinpointLocationEntityImpl>>
//
//    @Query("SELECT * FROM pinpoint_location")
//    abstract fun pinpointLocations(): List<PinpointLocationEntityImpl>
//
//    @Query("DELETE FROM pinpoint_location")
//    abstract fun deleteAll()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(pinpointLocationEntityImplList: List<PinpointLocationEntityImpl?>?)
//
//    @Query("SELECT * FROM pinpoint_location_copy")
//    abstract fun pinpointLocationOfCopyLiveData(): LiveData<List<PinpointLocationOfCopyEntityImpl>>
//
//    @Query("SELECT * FROM pinpoint_location_copy")
//    abstract fun pinpointLocationsOfCopy(): List<PinpointLocationOfCopyEntityImpl>
//
//    @Query("DELETE FROM pinpoint_location_copy")
//    abstract fun deleteAllOfCopy()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertOfCopy(pinpointLocationOfCopyEntityImplList: List<PinpointLocationOfCopyEntityImpl?>?)
//}