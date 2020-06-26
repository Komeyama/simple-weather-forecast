package com.komeyama.simple.weather.db

interface DetailForecastEntity {
    var id: Long
    var parentId: Long
    var date: String
    var dateLabel: String
    var telop: String
    var image: String
    var temperature: String
}