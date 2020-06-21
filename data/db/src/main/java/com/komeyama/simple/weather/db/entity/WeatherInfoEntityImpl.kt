package com.komeyama.simple.weather.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.model.ForecastInfo
import com.komeyama.simple.weather.db.WeatherInfoEntity
import com.komeyama.simple.weather.model.PrefectureIds

@Entity(tableName = "weather_info")
internal data class WeatherInfoEntityImpl(
    @PrimaryKey override var id: String,
    @Embedded override var cityID: PrefectureIds,
    @Embedded override var info: ForecastInfo
) : WeatherInfoEntity