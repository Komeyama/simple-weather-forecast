package com.komeyama.simple.weather.db

interface ForecastMainInfoEntity {
    var id: Int?
    val coord: CoordInfoEntity?
    var base: String?
    val main: MainInfoEntity?
    var visibility: String?
    val wind: WindInfoEntity?
    val clouds: CloudsInfoEntity?
    val sys: SysInfo?
    var timezone: Int?
    var name: String
    var cod: Int?
}

interface CoordInfoEntity {
    val lon: Float?
    val lat: Float?
}

interface MainInfoEntity {
    var temp: Float?
    var feels_like: Float?
    var temp_min: Float?
    var temp_max: Float?
    var pressure: Float?
    var humidity: Float?
}

interface WindInfoEntity {
    var speed: Float?
    var deg: Float?
}

interface CloudsInfoEntity {
    var all: Int?
}

interface SysInfo {
    var type: Int?
    var id: Int?
    var country: String?
    var sunrise: Int?
    var sunset: Int?
}