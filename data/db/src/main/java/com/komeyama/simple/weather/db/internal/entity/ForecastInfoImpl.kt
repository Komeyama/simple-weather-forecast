package com.komeyama.simple.weather.db.internal.entity

import androidx.room.*
import com.komeyama.simple.weather.db.ForecastInfoEntity

internal data class ForecastInfoImpl(
    @Embedded
    override var forecastInfoEntity: ForecastMainInfoEntityImpl,
    @Relation(
        parentColumn = "forecast_id",
        entityColumn = "parent_id"
    )
    override var detailForecastEntity: List<DetailForecastEntityImpl>,
    @Relation(
        parentColumn = "forecast_id",
        entityColumn = "parent_id"
    )
    override var pinpointLocationEntity: List<PinpointLocationEntityImpl>,
    @Relation(
        parentColumn = "forecast_id",
        entityColumn = "parent_id",
        entity = DetailCopyrightMainEntityImpl::class
    )
    override var detailCopyrightEntity: DetailCopyrightEntityImpl
) : ForecastInfoEntity