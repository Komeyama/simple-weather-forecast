package com.komeyama.simple.weather.db.internal.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.komeyama.simple.weather.db.DetailCopyrightMainEntity

@Entity(
    tableName = "detail_copyright",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = ForecastMainInfoEntityImpl::class,
            parentColumns = arrayOf("forecast_id"),
            childColumns = arrayOf("parent_id"),
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
class DetailCopyrightMainEntityImpl(
    @PrimaryKey @ColumnInfo(name = "copyright_id")
    override var copyrightID: Long,
    @ColumnInfo(name = "parent_id")
    override var parentId: Long,
    @ColumnInfo(name = "title")
    override var title: String,
    @ColumnInfo(name = "link")
    override var link: String,
    @Embedded(prefix = "detail_image")
    override var image: DetailImageEntityImpl
) : DetailCopyrightMainEntity