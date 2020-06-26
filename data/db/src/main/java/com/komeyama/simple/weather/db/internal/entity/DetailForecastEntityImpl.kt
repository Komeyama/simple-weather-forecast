package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
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
class DetailForecastEntityImpl(
    @PrimaryKey(autoGenerate = false)
    override var id: Long,
    @ColumnInfo(name = "parent_id")
    override var parentId: Long,
    @ColumnInfo(name = "date")
    override var date: String,
    @ColumnInfo(name = "date_label")
    override var dateLabel: String,
    @ColumnInfo(name = "telop")
    override var telop: String,
    @ColumnInfo(name = "image")
    override var image: String,
    @ColumnInfo(name = "temperature")
    override var temperature: String
) : DetailForecastEntity