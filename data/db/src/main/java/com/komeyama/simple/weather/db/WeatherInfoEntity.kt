package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.model.ForecastInfo
import com.komeyama.simple.weather.model.PrefectureIds

interface WeatherInfoEntity {
    var id: String
    var cityID: PrefectureIds
    var info: ForecastInfo
}