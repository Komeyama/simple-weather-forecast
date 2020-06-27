package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImpl

interface DetailCopyrightEntity {
    var title: String
    var link: String
    var image: DetailImageEntityImpl
}