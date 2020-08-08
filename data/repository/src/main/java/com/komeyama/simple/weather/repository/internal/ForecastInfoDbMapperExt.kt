package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.db.FavoritePlaceEntity
import com.komeyama.simple.weather.db.ForecastInfoEntity
import com.komeyama.simple.weather.model.*

internal fun List<FavoritePlaceEntity>.toStringList(): List<String> {
    return this.map {
        it.forecastId
    }
}

internal fun List<ForecastInfoEntity>.toForecastInfoList(): List<ForecastInfo> {
    return this.map {
        it.toForecastInfo()
    }
}

internal fun ForecastInfoEntity.toForecastInfo(): ForecastInfo {
    return ForecastInfo(
        coord = CoordInfo(
            lon = forecastInfoEntity.coord?.lon,
            lat = forecastInfoEntity.coord?.lat
        ),
        weather = weatherEntity.map {
            WeatherInfo(
                id = it.id,
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        base = forecastInfoEntity.base,
        main = MainInfo(
            temp = forecastInfoEntity.main?.temp,
            feels_like = forecastInfoEntity.main?.feels_like,
            temp_min = forecastInfoEntity.main?.temp_min,
            temp_max = forecastInfoEntity.main?.temp_max,
            pressure = forecastInfoEntity.main?.pressure,
            humidity = forecastInfoEntity.main?.humidity
        ),
        visibility = forecastInfoEntity.visibility,
        wind = WindInfo(
            speed = forecastInfoEntity.wind?.speed,
            deg = forecastInfoEntity.wind?.deg
        ),
        clouds = CloudsInfo(
            all = forecastInfoEntity.clouds?.all
        ),
        sys = SysInfo(
            type = forecastInfoEntity.sys?.type,
            id = forecastInfoEntity.sys?.id,
            country = forecastInfoEntity.sys?.country,
            sunrise = forecastInfoEntity.sys?.sunrise,
            sunset = forecastInfoEntity.sys?.sunset
        ),
        timezone = forecastInfoEntity.timezone,
        id = forecastInfoEntity.id,
        name = forecastInfoEntity.name,
        cod = forecastInfoEntity.cod

        /*
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
        */
    )
}

/*
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
*/