package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.Response
import com.komeyama.simple.weather.db.SubResponse
import com.komeyama.simple.weather.db.internal.entity.DetailLocationEntityImpl
import com.komeyama.simple.weather.db.internal.entity.ForecastInfoEntityImpl


internal fun List<Response>.toForecastInfoEntities(): List<ForecastInfoEntityImpl> =
    this.map {
        it.toForecastInfoEntity()
    }

internal fun Response.toForecastInfoEntity(): ForecastInfoEntityImpl {
    return ForecastInfoEntityImpl(
        id = id,
        cityID = cityID,
        detailLocation = dummy.toDetailLocationEntity()
    )
}

internal fun SubResponse.toDetailLocationEntity(): DetailLocationEntityImpl {
    return DetailLocationEntityImpl(
        area = area,
        prefecture = prefecture,
        city = city
    )
}