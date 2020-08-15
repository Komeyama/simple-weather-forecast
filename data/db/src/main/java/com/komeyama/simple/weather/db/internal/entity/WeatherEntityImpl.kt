package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.WeatherEntity

@Entity(
    tableName = "weather_info",
    foreignKeys = [ForeignKey(
        entity = ForecastMainInfoEntityImpl::class,
        parentColumns = arrayOf("forecast_id"),
        childColumns = arrayOf("parent_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class WeatherEntityImpl(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "weather_id")
    override var weatherId: Int? = 0,
    @ColumnInfo(name = "main")
    override var main: String,
    @ColumnInfo(name = "description")
    override var description: String,
    @ColumnInfo(name = "icon")
    override var icon: String,
    @ColumnInfo(name = "parent_id")
    override var parentId: String
) : WeatherEntity {
    companion object {
        fun empty(parentId: String) = WeatherEntityImpl(
            id = 0,
            main = "",
            description = "",
            icon = "",
            parentId = parentId
        )
    }
}
