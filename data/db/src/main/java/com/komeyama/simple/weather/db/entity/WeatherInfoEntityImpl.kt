package com.komeyama.simple.weather.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.model.ForecastInfo
import com.komeyama.simple.weather.db.WeatherInfoEntity

@Entity(tableName = "weather_info")
data class WeatherInfoEntityImpl(
    @PrimaryKey override var id: String,
    override var cityID: String,
    @Embedded override var dummyInfo: String
) : WeatherInfoEntity