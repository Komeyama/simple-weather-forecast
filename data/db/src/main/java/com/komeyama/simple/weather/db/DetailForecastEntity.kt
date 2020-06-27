package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImpl

interface DetailForecastEntity {
    var id: Long
    var parentId: Long
    var date: String
    var dateLabel: String
    var telop: String
    var image: DetailImageEntityImpl
    var temperature: String
}