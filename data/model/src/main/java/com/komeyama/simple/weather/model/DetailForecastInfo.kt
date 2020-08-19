package com.komeyama.simple.weather.model

import kotlinx.serialization.Serializable

@Serializable
data class DetailForecastInfo (
    var cod: String,
    var message: Int,
    var cnt: Int,
    var list: List<DetailWeatherInfo>,
    var city: CityInfo
)

@Serializable
data class DetailWeatherInfo (
    var dt: Int,
    var main: DetailMain,
    var weather: List<WeatherInfo>,
    var clouds: CloudsInfo,
    var wind: WindInfo,
    var visibility: Int,
    var pop: Float,
    var sys: DetailSys,
    var dt_txt: String
)

@Serializable
data class DetailMain(
    var temp: Float,
    var feels_like: Float,
    var temp_min: Float,
    var temp_max: Float,
    var pressure: Float,
    var sea_level: Float,
    var grnd_level: Float,
    var humidity: Float,
    var temp_kf: Float
)

@Serializable
data class  CityInfo (
    var id: Int,
    var name: String,
    var coord: CoordInfo,
    var country: String,
    var timezone: Int,
    var sunrise: Int,
    var sunset: Int
)

@Serializable
data class DetailSys (
    var pod: String
)