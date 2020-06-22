package com.komeyama.simple.weather.db.internal.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.ForecastInfoEntity
import com.komeyama.simple.weather.db.Response

@Entity(tableName = "forecast_info")
data class ForecastInfoEntityImpl(
    @PrimaryKey override var id: String,
    override var cityID: String,
    /**
     * todo add @Embedded
     */
    override var detailLocation: String
) : ForecastInfoEntity

internal fun List<Response>.toForecastInfoEntities(): List<ForecastInfoEntityImpl> =
    this.map {
        it.toForecastInfoEntityImpl()
    }

internal fun Response.toForecastInfoEntityImpl(): ForecastInfoEntityImpl {
    return ForecastInfoEntityImpl(
        id = id,
        cityID = cityID,
        detailLocation = dummy
    )
}