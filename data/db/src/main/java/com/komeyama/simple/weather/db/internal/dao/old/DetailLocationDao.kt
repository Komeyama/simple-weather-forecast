package com.komeyama.simple.weather.db.internal.dao.old

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.komeyama.simple.weather.db.internal.entity.old.DetailLocationEntityImpl

//@Dao
//internal abstract class DetailLocationDao {
//    @Query("SELECT * FROM detail_location")
//    abstract fun detailLocationLiveData(): LiveData<List<DetailLocationEntityImpl>>
//
//    @Query("SELECT * FROM detail_location")
//    abstract fun detailLocationInfo(): List<DetailLocationEntityImpl>
//
//    @Query("DELETE FROM detail_location")
//    abstract fun deleteAll()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(detailLocationEntityImplList: List<DetailLocationEntityImpl>)
//}