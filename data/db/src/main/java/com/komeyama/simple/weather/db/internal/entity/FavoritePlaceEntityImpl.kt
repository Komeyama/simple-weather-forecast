package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.FavoritePlaceEntity

@Entity(tableName = "favorite_place")
internal data class FavoritePlaceEntityImpl (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "favorite_forecast_id")
    override var forecastId: String?
): FavoritePlaceEntity