package com.komeyama.simple.weather.model

fun Float.toFromKelvinToCelsius(): Float {
    return this - 273.15F
}