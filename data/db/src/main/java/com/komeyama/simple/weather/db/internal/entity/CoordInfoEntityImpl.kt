package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.CoordInfoEntity

@Entity(tableName = "coord_info")
internal data class CoordInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "coord_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "lon")
    override var lon: Float?,
    @ColumnInfo(name = "lat")
    override var lat: Float?
) : CoordInfoEntity
