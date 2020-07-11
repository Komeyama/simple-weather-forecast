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
            this.forecastInfoEntity.detailLocation?.area,
            this.forecastInfoEntity.detailLocation?.prefecture,
            this.forecastInfoEntity.detailLocation?.city
        ),
        title = this.forecastInfoEntity.title,
        link = this.forecastInfoEntity.link,
        publicTime = this.forecastInfoEntity.publicTime,
        description = DetailDescription(
            this.forecastInfoEntity.description?.text,
            this.forecastInfoEntity.description?.publicTime
        ),
        forecasts = this.detailForecastEntity.map {
            it.toDetailForecasts()
        },
        pinpointLocations = this.pinpointLocationEntity.map {
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
            provider = this.detailCopyrightEntity.pinpointLocationEntity.map {
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