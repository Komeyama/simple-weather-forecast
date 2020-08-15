package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.MainInfoEntity

@Entity(tableName = "main_info")
internal data class MainInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "main_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "temp")
    override var temp: Float?,
    @ColumnInfo(name = "feels_like")
    override var feels_like: Float?,
    @ColumnInfo(name = "temp_min")
    override var temp_min: Float?,
    @ColumnInfo(name = "temp_max")
    override var temp_max: Float?,
    @ColumnInfo(name = "pressure")
    override var pressure: Float?,
    @ColumnInfo(name = "humidity")
    override var humidity: Float?
) : MainInfoEntity