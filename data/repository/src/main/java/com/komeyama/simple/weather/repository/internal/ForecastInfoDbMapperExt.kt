package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.db.FavoritePlaceEntity
import com.komeyama.simple.weather.db.ForecastInfoEntity
import com.komeyama.simple.weather.model.*

fun List<FavoritePlaceEntity>.toStringList(): List<String> {
    return this.map {
        it.forecastId
    }
}

fun List<ForecastInfoEntity>.toForecastInfoList(): List<ForecastInfo> {
    return this.map {
        it.toForecastInfo()
    }
}

fun ForecastInfoEntity.toForecastInfo(): ForecastInfo {
    return ForecastInfo(
        coord = CoordInfo(
            lon = forecastInfoEntity.coord?.lon,
            lat = forecastInfoEntity.coord?.lat
        ),
        weather = weatherEntity.map {
            WeatherInfo(
                id = it.weatherId,
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
    )
}