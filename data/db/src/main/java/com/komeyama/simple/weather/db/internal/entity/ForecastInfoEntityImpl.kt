package com.komeyama.simple.weather.db.internal.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.ForecastInfoEntity
import com.komeyama.simple.weather.db.Response
import com.komeyama.simple.weather.db.SubResponse

@Entity(tableName = "forecast_info")
data class ForecastInfoEntityImpl(
    @PrimaryKey override var id: String,
    override var cityID: String,
    @Embedded override var detailLocation: DetailLocationEntityImpl
) : ForecastInfoEntity