package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailDescriptionEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailLocationEntityImpl

interface ForecastMainInfoEntity {
    var forecastId: Int
    var title: String?
    var link: String?
    var publicTime: String?
    var detailLocation: DetailLocationEntityImpl?
    var description: DetailDescriptionEntityImpl?
}