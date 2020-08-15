package com.komeyama.simple.weather.db

interface MainInfoEntity {
    var temp: Float?
    var feels_like: Float?
    var temp_min: Float?
    var temp_max: Float?
    var pressure: Float?
    var humidity: Float?
}