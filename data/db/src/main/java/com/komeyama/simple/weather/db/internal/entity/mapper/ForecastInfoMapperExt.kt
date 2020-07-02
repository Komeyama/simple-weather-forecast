package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.*
import com.komeyama.simple.weather.model.*

internal fun List<ForecastInfo?>.toForecastMainInfoEntities(): List<ForecastMainInfoEntityImpl?>? =
    this.map {
        it?.toForecastMainInfoEntity()
    }

internal fun ForecastInfo.toForecastMainInfoEntity(): ForecastMainInfoEntityImpl {
    return ForecastMainInfoEntityImpl(
        0,
        title,
        link,
        publicTime,
        detailLocation = DetailLocationEntityImpl(
            0,
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

internal fun DetailCopyright.toDetailCopyrightMainEntity(): DetailCopyrightMainEntityImpl {
    return DetailCopyrightMainEntityImpl(
        parentId = 0, title = title, link = link, image = DetailImageEntityImplOfCopyright(
            title = image?.title ?: "",
            url = image?.url,
            width = image?.width,
            height = image?.height
        )
    )
}

internal fun List<PinpointLocation?>.toPinpointLocationOfCopyEntities(): List<PinpointLocationOfCopyEntityImpl?> =
    this.map {
        it.toPinpointLocationOfCopyEntity()
    }

internal fun PinpointLocation?.toPinpointLocationOfCopyEntity(): PinpointLocationOfCopyEntityImpl {
    return PinpointLocationOfCopyEntityImpl(
        parentId = 0,
        link = this?.link,
        name = this?.name
    )
}

internal fun List<PinpointLocation?>.toPinpointLocationEntities(): List<PinpointLocationEntityImpl?> =
    this.map {
        it?.toPinpointLocationEntity()
    }

internal fun PinpointLocation.toPinpointLocationEntity(): PinpointLocationEntityImpl? {
    return PinpointLocationEntityImpl(
        parentId = 0,
        link = link,
        name = name
    )
}

internal fun List<DetailForecasts>.toDetailForecastEntities(): List<DetailForecastEntityImpl> =
    this.map {
        it.toDetailForecastEntity()
    }

internal fun DetailForecasts.toDetailForecastEntity(): DetailForecastEntityImpl {
    return DetailForecastEntityImpl(
        0,
        parentId = 0,
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