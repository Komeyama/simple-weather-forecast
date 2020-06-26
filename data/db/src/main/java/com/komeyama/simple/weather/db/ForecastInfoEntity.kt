package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailForecastEntityImpl
import com.komeyama.simple.weather.db.internal.entity.ForecastMainInfoEntityImpl

interface ForecastInfoEntity {
    var forecastInfoEntityImpl: ForecastMainInfoEntityImpl
    var detailForecastEntityImpl: List<DetailForecastEntityImpl>
}