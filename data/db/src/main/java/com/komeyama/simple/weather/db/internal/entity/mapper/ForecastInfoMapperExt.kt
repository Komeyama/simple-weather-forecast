package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.DetailDescriptionEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailLocationEntityImpl
import com.komeyama.simple.weather.db.internal.entity.ForecastInfoEntityImpl
import com.komeyama.simple.weather.model.DetailDescriptionResponse
import com.komeyama.simple.weather.model.DetailLocationResponse
import com.komeyama.simple.weather.model.Response


internal fun List<Response>.toForecastInfoEntities(): List<ForecastInfoEntityImpl> =
    this.map {
        it.toForecastInfoEntity()
    }

internal fun Response.toForecastInfoEntity(): ForecastInfoEntityImpl {
    return ForecastInfoEntityImpl(
        id = id,
        cityID = cityID,
        description = detailDescriptionResponse.toDetailDescription(),
        detailLocation = detailLocationResponse.toDetailLocationEntity()
    )
}

internal fun DetailDescriptionResponse.toDetailDescription(): DetailDescriptionEntityImpl {
    return DetailDescriptionEntityImpl(
        text = text,
        publicTime = publicTime
    )
}

internal fun DetailLocationResponse.toDetailLocationEntity(): DetailLocationEntityImpl {
    return DetailLocationEntityImpl(
        area = area,
        prefecture = prefecture,
        city = city
    )
}