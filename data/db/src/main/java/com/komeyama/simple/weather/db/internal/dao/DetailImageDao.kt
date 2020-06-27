package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImplOfCopyright

@Dao
internal abstract class DetailImageDao {
    @Query("SELECT * FROM detail_image")
    abstract fun detailImageLiveData(): LiveData<List<DetailImageEntityImpl>>

    @Query("SELECT * FROM detail_image")
    abstract fun detailImageInfo(): List<DetailImageEntityImpl>

    @Query("DELETE FROM detail_image")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(detailImage: List<DetailImageEntityImpl>)

    @Query("SELECT * FROM detail_image_copyright")
    abstract fun detailImageOfCopyrightLiveData(): LiveData<List<DetailImageEntityImplOfCopyright>>

    @Query("SELECT * FROM detail_image_copyright")
    abstract fun detailImageOfCopyrightInfo(): List<DetailImageEntityImplOfCopyright>

    @Query("DELETE FROM detail_image_copyright")
    abstract fun deleteAllOfCopyright()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOfCopyright(detailImage: List<DetailImageEntityImplOfCopyright>)
}