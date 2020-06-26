package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.*
import com.komeyama.simple.weather.model.*


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

internal fun List<PinpointLocationResponse>.toPinpointLocationEntities(): List<PinpointLocationEntityImpl> =
    this.map {
        it.toPinpointLocationEntity()
    }

internal fun PinpointLocationResponse.toPinpointLocationEntity(): PinpointLocationEntityImpl {
    return PinpointLocationEntityImpl(
        id = id,
        parentId = parentId,
        link = link,
        name = name
    )
}