package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.FavoritePlaceEntity

@Entity(tableName = "favorite_place")
data class FavoritePlaceEntityImpl (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "favorite_forecast_id")
    override var forecastId: String?,
    @ColumnInfo(name = "is_favorite")
    override var isFavorite: Boolean
): FavoritePlaceEntity