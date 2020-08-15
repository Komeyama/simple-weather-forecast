package com.komeyama.simple.weather.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastInfo(
    var coord: CoordInfo?,
    var weather: List<WeatherInfo>?,
    var base: String?,
    var main: MainInfo?,
    var visibility: String?,
    var wind: WindInfo,
    var clouds: CloudsInfo,
    var sys: SysInfo?,
    var timezone: Int?,
    var id: Int?,
    var name: String?,
    var cod: Int?,
    var isFavorite: Boolean = false
)

@Serializable
data class CoordInfo(
    var lon: Float?,
    var lat: Float?
)

@Serializable
data class WeatherInfo(
    var id: Int?,
    var main: String?,
    var description: String?,
    var icon: String?
)

@Serializable
data class MainInfo(
    var temp: Float?,
    var feels_like: Float?,
    var temp_min: Float?,
    var temp_max: Float?,
    var pressure: Float?,
    var humidity: Float?
)

@Serializable
data class WindInfo(
    var speed: Float?,
    var deg: Float?
)

@Serializable
data class CloudsInfo(
    var all: Int?
)

@Serializable
data class SysInfo(
    var type: Int? = 0,
    var id: Int? = 0,
    var country: String?,
    var sunrise: Int?,
    var sunset: Int?
)