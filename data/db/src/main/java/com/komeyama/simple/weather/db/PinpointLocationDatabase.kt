package com.komeyama.simple.weather.db

interface PinpointLocationDatabase {
    fun pinpointLocationEntity(): List<PinpointLocationEntity>
}