package com.komeyama.simple.weather.db.internal.entity

import androidx.room.*
import com.komeyama.simple.weather.db.DetailForecastEntity

@Entity(
    tableName = "detail_forecast",
    foreignKeys = [ForeignKey(
        entity = ForecastMainInfoEntityImpl::class,
        parentColumns = arrayOf("forecast_id"),
        childColumns = arrayOf("parent_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DetailForecastEntityImpl(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "parent_id")
    override var parentId: Int,
    @ColumnInfo(name = "date")
    override var date: String?,
    @ColumnInfo(name = "date_label")
    override var dateLabel: String?,
    @ColumnInfo(name = "telop")
    override var telop: String?,
    @Embedded(prefix = "image")
    override var image: DetailImageEntityImpl?,
    @Embedded(prefix = "temperature")
    override var temperature: TemperatureEntityImpl?
) : DetailForecastEntity