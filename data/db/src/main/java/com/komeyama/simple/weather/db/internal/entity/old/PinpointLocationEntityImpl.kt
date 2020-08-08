package com.komeyama.simple.weather.db.internal.entity.old

//@Entity(
//    tableName = "pinpoint_location",
//    foreignKeys = [
//        ForeignKey(
//            entity = ForecastMainInfoEntityImpl::class,
//            parentColumns = arrayOf("forecast_id"),
//            childColumns = arrayOf("parent_id"),
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//internal data class PinpointLocationEntityImpl(
//    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "pinpoint_location_id")
//    override var id: Int = 0,
//    @ColumnInfo(name = "parent_id")
//    override var parentId: Int,
//    @ColumnInfo(name = "link")
//    override var link: String?,
//    @ColumnInfo(name = "name")
//    override var name: String?
//) : PinpointLocationEntity