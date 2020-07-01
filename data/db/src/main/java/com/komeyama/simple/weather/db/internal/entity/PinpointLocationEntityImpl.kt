package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.PinpointLocationEntity

@Entity(
    tableName = "pinpoint_location",
    foreignKeys = [
        ForeignKey(
            entity = ForecastMainInfoEntityImpl::class,
            parentColumns = arrayOf("forecast_id"),
            childColumns = arrayOf("parent_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PinpointLocationEntityImpl(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "pinpoint_location_id")
    var id: Int = 0,
    @ColumnInfo(name = "parent_id")
    override var parentId: Int,
    @ColumnInfo(name = "link")
    override var link: String?,
    @ColumnInfo(name = "name")
    override var name: String?
) : PinpointLocationEntity