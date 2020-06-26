package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.DetailDescriptionEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailForecastEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailLocationEntityImpl
import com.komeyama.simple.weather.db.internal.entity.ForecastMainInfoEntityImpl
import com.komeyama.simple.weather.model.DetailDescriptionResponse
import com.komeyama.simple.weather.model.DetailForecastResponse
import com.komeyama.simple.weather.model.DetailLocationResponse
import com.komeyama.simple.weather.model.MainResponse


internal fun List<MainResponse>.toForecastMainInfoEntities(): List<ForecastMainInfoEntityImpl> =
    this.map {
        it.toForecastMainInfoEntity()
    }

internal fun MainResponse.toForecastMainInfoEntity(): ForecastMainInfoEntityImpl {
    return ForecastMainInfoEntityImpl(
        forecastId = id,
        title = title,
        link = link,
        publicTime = publicTime,
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

internal fun List<DetailForecastResponse>.toDetailForecastEntities(): List<DetailForecastEntityImpl> =
    this.map {
        it.toDetailForecastEntity()
    }

internal fun DetailForecastResponse.toDetailForecastEntity(): DetailForecastEntityImpl {
    return DetailForecastEntityImpl(
        id = id,
        parentId = parentId,
        date = date,
        dateLabel = dateLabel,
        telop = telop,
        image = image,
        temperature = temperature
    )
}