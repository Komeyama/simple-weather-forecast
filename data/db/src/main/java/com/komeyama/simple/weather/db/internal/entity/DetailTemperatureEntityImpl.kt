package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailTemperatureEntity

@Entity(tableName = "detail_temperature")
class DetailTemperatureEntityImpl (
    @PrimaryKey @ColumnInfo(name = "celsius")
    override var celsius: String,
    @ColumnInfo(name = "fahrenheit")
    override var fahrenheit: String
): DetailTemperatureEntity