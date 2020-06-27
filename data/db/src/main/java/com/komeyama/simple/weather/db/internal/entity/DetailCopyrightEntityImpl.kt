package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailCopyrightEntity

@Entity(tableName = "detail_copyright")
class DetailCopyrightEntityImpl(
    @PrimaryKey @ColumnInfo(name = "title")
    override var title: String,
    @ColumnInfo(name = "link")
    override var link: String,
    @Embedded(prefix = "detail_image")
    override var image: DetailImageEntityImpl
) : DetailCopyrightEntity