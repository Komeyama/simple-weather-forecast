package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.PinpointLocationEntity

@Entity(tableName = "pinpoint_location_copy")
data class PinpointLocationOfCopyEntityImpl (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "parent_id")
    override var parentId: Long,
    @ColumnInfo(name = "link")
    override var link: String,
    @ColumnInfo(name = "name")
    override var name: String
): PinpointLocationEntity