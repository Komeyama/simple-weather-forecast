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
    var type: Int,
    var id: Int,
    var country: String?,
    var sunrise: Int?,
    var sunset: Int?
)

/// old
@Serializable
data class DetailLocation(
    var area: String?,
    var prefecture: String?,
    var city: String?
)

@Serializable
data class DetailDescription(
    var text: String?,
    var public_time: String?
)

@Serializable
data class DetailForecasts(
    var date: String?,
    var date_label: String?,
    var telop: String?,
    var image: DetailImage?,
    var temperature: Temperature?
)

@Serializable
data class PinpointLocation(
    var link: String?,
    var name: String?
)

@Serializable
data class DetailImage(
    var title: String?,
    var url: String?,
    var width: String?,
    var height: String?
)

@Serializable
data class Temperature(
    var min: DetailTemperature?,
    var max: DetailTemperature?
)

@Serializable
data class DetailTemperature(
    var celsius: String?,
    var fahrenheit: String?
)

@Serializable
data class DetailCopyright(
    var title: String?,
    var link: String?,
    var image: DetailImage?,
    var provider: List<PinpointLocation?>
)