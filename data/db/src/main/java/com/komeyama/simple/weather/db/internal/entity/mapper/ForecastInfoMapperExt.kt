package com.komeyama.simple.weather.db.internal.entity.mapper

import com.komeyama.simple.weather.db.internal.entity.*
import com.komeyama.simple.weather.model.*

internal fun ForecastInfo.toForecastMainInfoEntity(id: Int): ForecastMainInfoEntityImpl {
    return ForecastMainInfoEntityImpl(
        id = id,
        coord = CoordInfoEntityImpl(
            lon = coord?.lon,
            lat = coord?.lat
        ),
        base = base,
        main = MainInfoEntityImpl(
            temp = main?.temp,
            feels_like = main?.feels_like,
            temp_min = main?.temp_min,
            temp_max = main?.temp_max,
            pressure = main?.pressure,
            humidity = main?.humidity
        ),
        visibility = visibility,
        wind = WindInfoEntityImpl(
            speed = wind.speed,
            deg = wind.deg
        ),
        clouds = CloudsInfoEntityImpl(
            all = clouds.all
        ),
        sys = SysInfoImpl(
            id = sys?.id,
            type = sys?.type,
            country = sys?.country,
            sunrise = sys?.sunrise,
            sunset = sys?.sunset
        ),
        timezone = timezone,
        name = name ?: "",
        cod = cod
    )
}

internal fun ForecastInfo.toWeatherEntity(id: String): WeatherEntityImpl {
    return if (this.weather?.size == 0) {
        WeatherEntityImpl.empty(id)
    } else {
        WeatherEntityImpl(
            weatherId = this.weather?.get(0)?.id,
            main = this.weather?.get(0)?.main ?: "",
            description = this.weather?.get(0)?.description ?: "",
            icon = this.weather?.get(0)?.icon ?: "",
            parentId = id
        )
    }
}