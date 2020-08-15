package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.WindInfoEntity

@Entity(tableName = "wind_info")
internal data class WindInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "wind_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "speed")
    override var speed: Float?,
    @ColumnInfo(name = "deg")
    override var deg: Float?
) : WindInfoEntity