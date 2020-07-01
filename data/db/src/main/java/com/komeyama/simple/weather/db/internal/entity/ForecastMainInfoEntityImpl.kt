package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.ForecastMainInfoEntity

@Entity(tableName = "forecast_info")
data class ForecastMainInfoEntityImpl(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "forecast_id")
    override var forecastId: Int = 0,
    override var title: String,
    override var link: String,
    override var publicTime: String,
    @Embedded override var detailLocation: DetailLocationEntityImpl,
    @Embedded override var description: DetailDescriptionEntityImpl
) : ForecastMainInfoEntity