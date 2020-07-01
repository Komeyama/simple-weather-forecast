package com.komeyama.simple.weather.db.internal.entity

import androidx.room.*
import com.komeyama.simple.weather.db.PinpointLocationEntity

@Entity(
    tableName = "pinpoint_location_copy",
    foreignKeys = [
        ForeignKey(
            entity = DetailCopyrightMainEntityImpl::class,
            parentColumns = arrayOf("detail_copyright_main_id"),
            childColumns = arrayOf("pinpoint_location_copy_parent_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PinpointLocationOfCopyEntityImpl(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "pinpoint_copyright_id")
    var id: Int = 0,
    @ColumnInfo(name = "pinpoint_location_copy_parent_id")
    override var parentId: Int,
    @ColumnInfo(name = "link")
    override var link: String?,
    @ColumnInfo(name = "name")
    override var name: String?
) : PinpointLocationEntity