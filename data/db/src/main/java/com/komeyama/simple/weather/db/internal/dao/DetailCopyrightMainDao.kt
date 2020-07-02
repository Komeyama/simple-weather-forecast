package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.DetailCopyrightMainEntityImpl

@Dao
internal abstract class DetailCopyrightMainDao {
    @Query("SELECT * FROM detail_copyright")
    abstract fun detailCopyrightMainLiveData(): LiveData<List<DetailCopyrightMainEntityImpl>>

    @Query("SELECT * FROM detail_copyright")
    abstract fun detailCopyrightMainInfo(): List<DetailCopyrightMainEntityImpl>

    @Query("DELETE FROM detail_copyright")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(detailCopyrightMainEntities: DetailCopyrightMainEntityImpl?)
}