package com.komeyama.simple.weather.db.internal.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.komeyama.simple.weather.db.internal.entity.DetailCopyrightEntityImpl

@Dao
internal abstract class DetailCopyrightDao {
    @Transaction
    @Query("SELECT * FROM detail_copyright")
    abstract fun detailCopyLightInfo(): List<DetailCopyrightEntityImpl>
}