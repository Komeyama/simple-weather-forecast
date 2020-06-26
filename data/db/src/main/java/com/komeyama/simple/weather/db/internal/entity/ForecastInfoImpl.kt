package com.komeyama.simple.weather.db.internal.entity

import androidx.room.*
import com.komeyama.simple.weather.db.ForecastInfoEntity

data class ForecastInfoImpl(
    @Embedded
    override var forecastInfoEntityImpl: ForecastMainInfoEntityImpl,
    @Relation(
        parentColumn = "forecast_id",
        entityColumn = "parent_id"
    )
    override var detailForecastEntityImpl: List<DetailForecastEntityImpl>,
    @Relation(
        parentColumn = "forecast_id",
        entityColumn = "parent_id"
    )
    override var pinpointLocationEntityImpl: List<PinpointLocationEntityImpl>
) : ForecastInfoEntity