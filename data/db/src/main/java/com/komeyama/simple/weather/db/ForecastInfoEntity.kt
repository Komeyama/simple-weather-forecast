package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailDescriptionEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailLocationEntityImpl

interface ForecastInfoEntity {
    var id: String
    var cityID: String
    var detailLocation: DetailLocationEntityImpl
    var description: DetailDescriptionEntityImpl
}