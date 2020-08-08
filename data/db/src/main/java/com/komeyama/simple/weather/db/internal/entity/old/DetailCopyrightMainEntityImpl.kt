package com.komeyama.simple.weather.db.internal.entity.old

//@Entity(
//    tableName = "detail_copyright",
//    foreignKeys = [
//        ForeignKey(
//            entity = ForecastMainInfoEntityImpl::class,
//            parentColumns = arrayOf("forecast_id"),
//            childColumns = arrayOf("parent_id"),
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//internal data class DetailCopyrightMainEntityImpl(
//    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "detail_copyright_main_id")
//    var id: Int = 0,
//    @ColumnInfo(name = "parent_id")
//    override var parentId: Int,
//    @ColumnInfo(name = "title")
//    override var title: String?,
//    @ColumnInfo(name = "link")
//    override var link: String?,
//    @Embedded(prefix = "detail_image")
//    override var image: DetailImageEntityImplOfCopyright?
//) : DetailCopyrightMainEntity