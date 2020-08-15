package com.komeyama.simple.weather.db

interface WeatherEntity {
    var parentId: String
    var weatherId: Int?
    var main: String
    var description: String
    var icon: String
}