package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.*
import com.komeyama.simple.weather.model.*

internal fun List<ForecastInfo?>.toForecastMainInfoEntities(id: Int): List<ForecastMainInfoEntityImpl?>? =
    this.map {
        it?.toForecastMainInfoEntity(id)
    }

internal fun ForecastInfo.toForecastMainInfoEntity(id: Int): ForecastMainInfoEntityImpl {
    return ForecastMainInfoEntityImpl(
        id,
        title,
        link,
        publicTime,
        detailLocation = DetailLocationEntityImpl(
            id,
            location?.area,
            location?.prefecture,
            location?.city
        ),
        description = DetailDescriptionEntityImpl(
            text = description?.text,
            publicTime = description?.publicTime
        )
    )
}

internal fun DetailCopyright.toDetailCopyrightMainEntity(id: Int): DetailCopyrightMainEntityImpl {
    return DetailCopyrightMainEntityImpl(
        id = id,
        parentId = id,
        title = title,
        link = link,
        image = DetailImageEntityImplOfCopyright(
            title = image?.title ?: "",
            url = image?.url,
            width = image?.width,
            height = image?.height
        )
    )
}

internal fun List<PinpointLocation?>.toPinpointLocationOfCopyEntities(id: Int): List<PinpointLocationOfCopyEntityImpl?> =
    this.map {
        it.toPinpointLocationOfCopyEntity(id)
    }

internal fun PinpointLocation?.toPinpointLocationOfCopyEntity(id: Int): PinpointLocationOfCopyEntityImpl {
    return PinpointLocationOfCopyEntityImpl(
        parentId = id,
        link = this?.link,
        name = this?.name
    )
}

internal fun List<PinpointLocation?>.toPinpointLocationEntities(id: Int): List<PinpointLocationEntityImpl?> =
    this.map {
        it?.toPinpointLocationEntity(id)
    }

internal fun PinpointLocation.toPinpointLocationEntity(id: Int): PinpointLocationEntityImpl? {
    return PinpointLocationEntityImpl(
        parentId = id,
        link = link,
        name = name
    )
}

internal fun List<DetailForecasts>.toDetailForecastEntities(id: Int): List<DetailForecastEntityImpl> =
    this.map {
        it.toDetailForecastEntity(id)
    }

internal fun DetailForecasts.toDetailForecastEntity(id: Int): DetailForecastEntityImpl {
    return DetailForecastEntityImpl(
        parentId = id,
        date = date,
        dateLabel = dateLabel,
        telop = telop,
        image = DetailImageEntityImpl(
            title = image?.title ?: "",
            url = image?.url,
            width = image?.width,
            height = image?.height
        ),
        temperature = temperature?.toTemperatureEntity()
    )
}

internal fun Temperature.toTemperatureEntity(): TemperatureEntityImpl {
    return TemperatureEntityImpl(
        min = min?.toDetailTemperatureEntity(),
        max = max?.toDetailTemperatureEntity()
    )
}

internal fun DetailTemperature.toDetailTemperatureEntity(): DetailTemperatureEntityImpl {
    return DetailTemperatureEntityImpl(
        celsius = celsius,
        fahrenheit = fahrenheit
    )
}