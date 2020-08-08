package com.komeyama.simple.weather.db.internal.entity.old

//import com.komeyama.simple.weather.db.internal.entity.old.DetailCopyrightMainEntityImpl
//
//@Entity(
//    tableName = "pinpoint_location_copy",
//    foreignKeys = [
//        ForeignKey(
//            entity = DetailCopyrightMainEntityImpl::class,
//            parentColumns = arrayOf("detail_copyright_main_id"),
//            childColumns = arrayOf("pinpoint_location_copy_parent_id"),
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//internal data class PinpointLocationOfCopyEntityImpl(
//    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "pinpoint_copyright_id")
//    override var id: Int = 0,
//    @ColumnInfo(name = "pinpoint_location_copy_parent_id")
//    override var parentId: Int,
//    @ColumnInfo(name = "link")
//    override var link: String?,
//    @ColumnInfo(name = "name")
//    override var name: String?
//) : PinpointLocationEntity