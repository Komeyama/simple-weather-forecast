package com.komeyama.simple.weather.db.internal.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.ForecastInfoEntity

@Entity(tableName = "forecast_info")
data class ForecastInfoEntityImpl(
    @Embedded override var detailForecasts: DetailForecastEntityImpl,
    @PrimaryKey override var title: String,
    override var link: String,
    override var publicTime: String,
    @Embedded override var detailLocation: DetailLocationEntityImpl,
    @Embedded override var description: DetailDescriptionEntityImpl
) : ForecastInfoEntity