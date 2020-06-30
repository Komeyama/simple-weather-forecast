package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailTemperatureEntityImpl

interface TemperatureEntity {
    var min: DetailTemperatureEntityImpl?
    var max: DetailTemperatureEntityImpl?
}