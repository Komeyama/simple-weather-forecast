package com.komeyama.simple.weather.model

import kotlinx.serialization.Serializable

@Serializable
data class WeeklyForecastInfo (
    var lat: Float,
    var lon: Float,
    var timezone: String,
    var timezone_offset: String,
    var current: CurrentWeather,
    var daily: List<DailyWeather>
)

@Serializable
data class CurrentWeather (
    var dt: Int,
    var sunrise: Int,
    var sunset: Int,
    var temp: Float,
    var feels_like: Float,
    var pressure: Int,
    var humidity: Int,
    var dew_point: Float,
    var uvi: Float? = null,
    var clouds: Int,
    var visibility: Int,
    var wind_speed: Float,
    var wind_deg: Int,
    var weather: List<WeatherInfo>
)

@Serializable
data class DailyWeather (
    var dt: Int,
    var sunrise: Int,
    var sunset: Int,
    var temp:  DailyTemp,
    var feels_like: FeelsLike,
    var pressure: Int,
    var humidity: Int,
    var dew_point: Float,
    var wind_speed: Float,
    var wind_deg: Int,
    var weather: List<WeatherInfo>,
    var clouds: Int,
    var pop: Float,
    var uvi: Float? = null,
    var rain: Float? = null

)

@Serializable
data class DailyTemp(
    var day: Float,
    var min: Float,
    var max: Float,
    var night: Float,
    var eve: Float,
    var morn: Float
)

@Serializable
data class FeelsLike(
    var day: Float,
    var night: Float,
    var eve: Float,
    var morn: Float
)