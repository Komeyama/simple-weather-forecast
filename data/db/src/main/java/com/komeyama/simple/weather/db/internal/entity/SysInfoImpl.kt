package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.SysInfo

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