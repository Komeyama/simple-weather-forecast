package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.CloudsInfoEntity

@Entity(tableName = "clouds_info")
internal data class CloudsInfoEntityImpl(
    @PrimaryKey @ColumnInfo(name = "clouds_info_id")
    var id: Int = 0,
    @ColumnInfo(name = "all")
    override var all: Int?
) : CloudsInfoEntity