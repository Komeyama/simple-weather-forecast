package com.komeyama.simple.weather.db

interface DetailForecastEntity {
    var parentId: Int
    var date: String?
    var dateLabel: String?
    var telop: String?
    val image: DetailImageEntity?
    val temperature: TemperatureEntity?
}