package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailLocationEntityImpl

interface ForecastInfoEntity {
    var id: String
    var cityID: String

    /**
     * todo: string -> DetailLocationEntityImpl
     */
    var detailLocation: String
}