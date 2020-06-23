package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailDescriptionEntity

@Entity(tableName = "detail_description")
class DetailDescriptionEntityImpl(
    @PrimaryKey @ColumnInfo(name = "text")
    override var text: String,
    @ColumnInfo(name = "public_time")
    override var publicTime: String
) : DetailDescriptionEntity