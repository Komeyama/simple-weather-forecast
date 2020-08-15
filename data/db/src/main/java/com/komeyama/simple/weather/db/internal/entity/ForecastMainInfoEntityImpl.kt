package com.komeyama.simple.weather.db.internal.entity

import androidx.room.*
import com.komeyama.simple.weather.db.*

@Entity(tableName = "forecast_info")
internal data class ForecastMainInfoEntityImpl(
    override var id: Int?,
    @Embedded override var coord: CoordInfoEntityImpl?,
    override var base: String?,
    @Embedded override var main: MainInfoEntityImpl?,
    override var visibility: String?,
    @Embedded override var wind: WindInfoEntityImpl?,
    @Embedded override var clouds: CloudsInfoEntityImpl?,
    @Embedded override var sys: SysInfoImpl?,
    override var timezone: Int?,
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "forecast_id")
    override var name: String,
    override var cod: Int?
) : ForecastMainInfoEntity





