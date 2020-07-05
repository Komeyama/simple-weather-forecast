package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.db.DetailForecastEntity
import com.komeyama.simple.weather.db.ForecastInfoEntity
import com.komeyama.simple.weather.db.PinpointLocationEntity
import com.komeyama.simple.weather.model.*

internal fun List<ForecastInfoEntity>.toForecastInfoList(): List<ForecastInfo> {
    return this.map {
        it.toForecastInfo()
    }
}

internal fun ForecastInfoEntity.toForecastInfo(): ForecastInfo {
    return ForecastInfo(
        location = DetailLocation(
            this.forecastInfoEntityImpl.detailLocation?.area,
            this.forecastInfoEntityImpl.detailLocation?.prefecture,
            this.forecastInfoEntityImpl.detailLocation?.city
        ),
        title = this.forecastInfoEntityImpl.title,
        link = this.forecastInfoEntityImpl.link,
        publicTime = this.forecastInfoEntityImpl.publicTime,
        description = DetailDescription(
            this.forecastInfoEntityImpl.description?.text,
            this.forecastInfoEntityImpl.description?.publicTime
        ),
        forecasts = this.detailForecastEntityImpl.map {
            it.toDetailForecasts()
        },
        pinpointLocations = this.pinpointLocationEntityImpl.map {
            it.toPinpointLocation()
        },
        copyright = DetailCopyright(
            title = this.detailCopyrightEntity.detailCopyrightMainEntity.title,
            link = this.detailCopyrightEntity.detailCopyrightMainEntity.link,
            image = DetailImage(
                this.detailCopyrightEntity.detailCopyrightMainEntity.image?.title,
                this.detailCopyrightEntity.detailCopyrightMainEntity.image?.url,
                this.detailCopyrightEntity.detailCopyrightMainEntity.image?.width,
                this.detailCopyrightEntity.detailCopyrightMainEntity.image?.height
            ),
            provider = this.detailCopyrightEntity.pinpointLocationOfCopyEntityImpl.map {
                it?.toPinpointLocation()
            }
        )
    )
}

internal fun DetailForecastEntity.toDetailForecasts(): DetailForecasts {
    return DetailForecasts(
        date = date,
        dateLabel = this.dateLabel,
        telop = this.telop,
        image = DetailImage(
            this.image?.title,
            this.image?.url,
            this.image?.width,
            this.image?.height
        ),
        temperature = Temperature(
            min = DetailTemperature(
                this.temperature?.min?.celsius,
                this.temperature?.min?.fahrenheit
            ),
            max = DetailTemperature(
                this.temperature?.max?.celsius,
                this.temperature?.max?.fahrenheit
            )
        )
    )
}

internal fun PinpointLocationEntity.toPinpointLocation(): PinpointLocation {
    return PinpointLocation(
        link = this.link,
        name = this.name
    )
}