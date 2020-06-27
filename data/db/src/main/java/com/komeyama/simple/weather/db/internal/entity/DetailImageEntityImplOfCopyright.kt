package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailImageEntity

@Entity(tableName = "detail_image_copyright")
class DetailImageEntityImplOfCopyright(
    @PrimaryKey @ColumnInfo(name = "title")
    override var title: String,
    @ColumnInfo(name = "link")
    override var link: String,
    @ColumnInfo(name = "url")
    override var url: String,
    @ColumnInfo(name = "width")
    override var width: String,
    @ColumnInfo(name = "height")
    override var height: String
) : DetailImageEntity