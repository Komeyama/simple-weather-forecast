package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailCopyrightMainEntityImpl
import com.komeyama.simple.weather.db.internal.entity.PinpointLocationOfCopyEntityImpl

interface DetailCopyrightEntity {
    var detailCopyrightMainEntity: DetailCopyrightMainEntityImpl
    var pinpointLocationOfCopyEntityImpl: List<PinpointLocationOfCopyEntityImpl?>
}