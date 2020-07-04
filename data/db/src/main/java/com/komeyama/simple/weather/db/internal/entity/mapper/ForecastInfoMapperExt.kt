package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.*
import com.komeyama.simple.weather.model.*

internal fun List<ForecastInfo?>.toForecastMainInfoEntities(id: Int): List<ForecastMainInfoEntityImpl?>? =
    this.map {
        it?.toForecastMainInfoEntity(id)
    }

internal fun ForecastInfo.toForecastMainInfoEntity(id: Int): ForecastMainInfoEntityImpl {
    return ForecastMainInfoEntityImpl(
        forecastId = id,
        title = title,
        link = link,
        publicTime = publicTime,
        detailLocation = DetailLocationEntityImpl(
            id = id,
            area = location?.area,
            prefecture = location?.prefecture,
            city = location?.city
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

internal fun List<PinpointLocation?>.toPinpointLocationEntities(
    parentId: Int,
    previousCount: Int
): List<PinpointLocationEntityImpl?> =
    this.mapIndexed { localIndex, pinpointLocation ->
        pinpointLocation?.toPinpointLocationEntity(localIndex + previousCount, parentId)
    }

internal fun PinpointLocation.toPinpointLocationEntity(
    localIndex: Int,
    parentId: Int
): PinpointLocationEntityImpl? {
    return PinpointLocationEntityImpl(
        id = localIndex,
        parentId = parentId,
        link = link,
        name = name
    )
}

internal fun List<DetailForecasts>.toDetailForecastEntities(parentId: Int): List<DetailForecastEntityImpl> =
    this.mapIndexed { localIndex, detailForecasts ->
        detailForecasts.toDetailForecastEntity(localIndex + (parentId * this.size), parentId)
    }

internal fun DetailForecasts.toDetailForecastEntity(
    localIndex: Int,
    parentId: Int
): DetailForecastEntityImpl {
    return DetailForecastEntityImpl(
        id = localIndex,
        parentId = parentId,
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