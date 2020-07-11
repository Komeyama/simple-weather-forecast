package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailTemperatureEntity

@Entity(tableName = "detail_temperature")
internal data class DetailTemperatureEntityImpl (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "celsius")
    override var celsius: String?,
    @ColumnInfo(name = "fahrenheit")
    override var fahrenheit: String?
): DetailTemperatureEntity