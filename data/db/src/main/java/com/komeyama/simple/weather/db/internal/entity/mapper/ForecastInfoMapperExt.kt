package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.*
import com.komeyama.simple.weather.model.*

internal fun List<MainResponse>.toForecastMainInfoEntities(): List<ForecastMainInfoEntityImpl> =
    this.map {
        it.toForecastMainInfoEntity()
    }

internal fun MainResponse.toForecastMainInfoEntity(): ForecastMainInfoEntityImpl {
    return ForecastMainInfoEntityImpl(
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
        parentId = 0,
        date = date,
        dateLabel = dateLabel,
        telop = telop,
        image = image.toDetailImageEntity(),
        temperature = temperature.toTemperatureEntity()
    )
}

internal fun TemperatureResponse.toTemperatureEntity(): TemperatureEntityImpl {
    return TemperatureEntityImpl(
        min = min?.toDetailTemperatureEntity(),
        max = max?.toDetailTemperatureEntity()
    )
}

internal fun DetailTemperatureResponse.toDetailTemperatureEntity(): DetailTemperatureEntityImpl? {
    return DetailTemperatureEntityImpl(
        celsius = celsius,
        fahrenheit = fahrenheit
    )
}

internal fun List<PinpointLocationResponse>.toPinpointLocationEntities(): List<PinpointLocationEntityImpl> =
    this.map {
        it.toPinpointLocationEntity()
    }

internal fun PinpointLocationResponse.toPinpointLocationEntity(): PinpointLocationEntityImpl {
    return PinpointLocationEntityImpl(
        parentId = 0,
        link = link,
        name = name
    )
}

internal fun List<DetailCopyrightResponse>.toDetailCopyrightEntities(): List<DetailCopyrightEntityImpl> =
    this.map {
        it.toDetailCopyrightEntity()
    }

internal fun DetailCopyrightResponse.toDetailCopyrightEntity(): DetailCopyrightEntityImpl {
    return DetailCopyrightEntityImpl(
        detailCopyrightMainEntity = DetailCopyrightMainEntityImpl(
            parentId = 0,
            title = title,
            link = link,
            image = image?.toDetailImageOfCompanyEntity()
        ),
        pinpointLocationOfCopyEntityImpl = provider.toPinpointLocationOfCopyEntities()
    )
}

internal fun List<PinpointLocationResponse?>.toPinpointLocationOfCopyEntities(): List<PinpointLocationOfCopyEntityImpl?> =
    this.map {
        it?.toPinpointLocationOfCopyEntity()
    }

internal fun PinpointLocationResponse.toPinpointLocationOfCopyEntity(): PinpointLocationOfCopyEntityImpl {
    return PinpointLocationOfCopyEntityImpl(
        parentId = 0,
        link = link,
        name = name
    )
}

internal fun DetailImageResponse.toDetailImageEntity(): DetailImageEntityImpl {
    return DetailImageEntityImpl(
        title = title,
        url = url,
        width = width,
        height = height
    )
}

internal fun DetailImageResponse.toDetailImageOfCompanyEntity(): DetailImageEntityImplOfCopyright {
    return DetailImageEntityImplOfCopyright(
        title = title,
        url = url,
        width = width,
        height = height
    )
}