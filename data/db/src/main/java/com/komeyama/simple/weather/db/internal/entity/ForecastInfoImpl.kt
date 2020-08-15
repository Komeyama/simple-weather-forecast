package com.komeyama.simple.weather.db.internal.entity

import androidx.room.*
import com.komeyama.simple.weather.db.ForecastInfoEntity

internal data class ForecastInfoImpl(
    @Embedded
    override val forecastInfoEntity: ForecastMainInfoEntityImpl,

    @Relation(
        parentColumn = "forecast_id",
        entityColumn = "parent_id"
    )
    override val weatherEntity: List<WeatherEntityImpl>

) : ForecastInfoEntity