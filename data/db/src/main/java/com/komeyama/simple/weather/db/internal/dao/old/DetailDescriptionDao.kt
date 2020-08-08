package com.komeyama.simple.weather.db.internal.dao.old

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.komeyama.simple.weather.db.internal.entity.old.DetailDescriptionEntityImpl

//@Dao
//internal abstract class DetailDescriptionDao {
//    @Query("SELECT * FROM detail_description")
//    abstract fun detailDescriptionLiveData(): LiveData<List<DetailDescriptionEntityImpl>>
//
//    @Query("SELECT * FROM detail_description")
//    abstract fun detailDescriptionInfo(): List<DetailDescriptionEntityImpl>
//
//    @Query("DELETE FROM detail_description")
//    abstract fun deleteAll()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(detailDescriptionEntityImplList: List<DetailDescriptionEntityImpl>)
//}