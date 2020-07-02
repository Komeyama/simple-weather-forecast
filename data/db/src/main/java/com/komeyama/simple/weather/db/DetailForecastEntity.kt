package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImpl
import com.komeyama.simple.weather.db.internal.entity.TemperatureEntityImpl

interface DetailForecastEntity {
    var parentId: Long
    var date: String?
    var dateLabel: String?
    var telop: String?
    var image: DetailImageEntityImpl?
    var temperature: TemperatureEntityImpl?
}