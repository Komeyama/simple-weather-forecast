package com.komeyama.simple.weather.db.internal.entity

import androidx.room.*
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.SysInfo

@Entity(tableName = "coord_info")
internal data class CoordInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "coord_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "lon")
    override var lon: Float?,
    @ColumnInfo(name = "lat")
    override var lat: Float?
) : CoordInfoEntity

@Entity(tableName = "main_info")
internal data class MainInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "main_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "temp")
    override var temp: Float?,
    @ColumnInfo(name = "feels_like")
    override var feels_like: Float?,
    @ColumnInfo(name = "temp_min")
    override var temp_min: Float?,
    @ColumnInfo(name = "temp_max")
    override var temp_max: Float?,
    @ColumnInfo(name = "pressure")
    override var pressure: Float?,
    @ColumnInfo(name = "humidity")
    override var humidity: Float?
) : MainInfoEntity

@Entity(tableName = "wind_info")
internal data class WindInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "wind_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "speed")
    override var speed: Float?,
    @ColumnInfo(name = "deg")
    override var deg: Float?
) : WindInfoEntity

@Entity(tableName = "clouds_info")
internal data class CloudsInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "clouds_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "all")
    override var all: Int?
) : CloudsInfoEntity

@Entity(tableName = "sys_info")
internal data class SysInfoImpl(
    @PrimaryKey @ColumnInfo(name = "sys_id")
    override var id: Int?,
    @ColumnInfo(name = "type")
    override var type: Int?,
    @ColumnInfo(name = "country")
    override var country: String?,
    @ColumnInfo(name = "sunrise")
    override var sunrise: Int?,
    @ColumnInfo(name = "sunset")
    override var sunset: Int?
) : SysInfo

@Entity(tableName = "forecast_info")
internal data class ForecastMainInfoEntityImpl(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "forecast_id")
    override var id: Int?,
    @Embedded override var coord: CoordInfoEntityImpl?,
    override var base: String?,
    @Embedded override var main: MainInfoEntityImpl?,
    override var visibility: String?,
    @Embedded override var wind: WindInfoEntityImpl?,
    @Embedded override var clouds: CloudsInfoEntityImpl?,
    @Embedded override var sys: SysInfoImpl?,
    override var timezone: Int?,
    override var name: String?,
    override var cod: Int?

//    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "forecast_id")
//    override var forecastId: Int = 0,
//    override var title: String?,
//    override var link: String?,
//    override var publicTime: String?,
//    @Embedded override var detailLocation: DetailLocationEntityImpl?,
//    @Embedded override var description: DetailDescriptionEntityImpl?


) : ForecastMainInfoEntity

interface WeatherEntity {
    var parentId: Int
    var id: Int?
    var main: String
    var description: String
    var icon: String
}

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
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "weather_id")
    override var id: Int?,
    @ColumnInfo(name = "main")
    override var main: String,
    @ColumnInfo(name = "description")
    override var description: String,
    @ColumnInfo(name = "icon")
    override var icon: String,
    @ColumnInfo(name = "parent_id")
    override var parentId: Int
) : WeatherEntity {
    companion object {
        val EMPTY = WeatherEntityImpl(
            id = 0,
            main = "",
            description = "",
            icon = "",
            parentId = 0
        )
    }
}


