package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.DetailCopyrightEntityImpl

@Dao
internal abstract class DetailCopyrightDao {
    @Query("SELECT * FROM detail_copyright")
    abstract fun detailCopyrightLiveData(): LiveData<List<DetailCopyrightEntityImpl>>

    @Query("SELECT * FROM detail_copyright")
    abstract fun detailCopyright(): List<DetailCopyrightEntityImpl>

    @Query("DELETE FROM detail_copyright")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(detailCopyrightEntities: List<DetailCopyrightEntityImpl>)
}