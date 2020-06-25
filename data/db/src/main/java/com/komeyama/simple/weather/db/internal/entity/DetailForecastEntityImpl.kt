package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailForecastEntity
import com.komeyama.simple.weather.model.DetailImage
import com.komeyama.simple.weather.model.DetailTemperature

@Entity(tableName = "detail_forecast")
class DetailForecastEntityImpl (
    @PrimaryKey @ColumnInfo(name = "date")
    override var date: String,
    @ColumnInfo(name = "date_label")
    override var dateLabel: String,
    @ColumnInfo(name = "telop")
    override var telop: String,
    @ColumnInfo(name = "image")
    override var image: String,
    @ColumnInfo(name = "temperature")
    override var temperature: String
): DetailForecastEntity