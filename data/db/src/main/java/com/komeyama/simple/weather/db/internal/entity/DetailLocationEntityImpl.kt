package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailLocationEntity

@Entity(tableName = "detail_location")
data class DetailLocationEntityImpl(
    @PrimaryKey @ColumnInfo(name = "detail_location_id")
    var id: Int = 0,
    @ColumnInfo(name = "area")
    override var area: String?,
    @ColumnInfo(name = "prefecture")
    override var prefecture: String?,
    @ColumnInfo(name = "city")
    override var city: String?
) : DetailLocationEntity