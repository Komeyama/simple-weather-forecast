package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImpl

interface DetailCopyrightMainEntity {
    var copyrightID: Long
    var parentId: Long
    var title: String
    var link: String
    var image: DetailImageEntityImpl
}