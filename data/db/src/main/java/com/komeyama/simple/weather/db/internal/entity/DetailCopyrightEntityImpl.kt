package com.komeyama.simple.weather.db.internal.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.komeyama.simple.weather.db.DetailCopyrightEntity

internal data class DetailCopyrightEntityImpl(
    @Embedded
    override var detailCopyrightMainEntity: DetailCopyrightMainEntityImpl,
    @Relation(
        parentColumn = "detail_copyright_main_id",
        entityColumn = "pinpoint_location_copy_parent_id"
    )
    override var pinpointLocationOfCopyEntityImpl: List<PinpointLocationOfCopyEntityImpl?>
) : DetailCopyrightEntity