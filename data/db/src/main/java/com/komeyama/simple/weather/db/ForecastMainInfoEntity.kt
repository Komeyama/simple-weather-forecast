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