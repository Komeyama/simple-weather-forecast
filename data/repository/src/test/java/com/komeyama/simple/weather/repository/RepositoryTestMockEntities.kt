package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.db.*

data class ForecastInfoEntityMockImpl(
    override val forecastInfoEntity: ForecastMainInfoEntity,
    override val weatherEntity: List<WeatherEntity>
) : ForecastInfoEntity

data class ForecastMainInfoEntityMockImpl(
    override var id: Int?,
    override val coord: CoordInfoEntity?,
    override var base: String?,
    override val main: MainInfoEntity?,
    override var visibility: String?,
    override val wind: WindInfoEntity?,
    override val clouds: CloudsInfoEntity?,
    override val sys: SysInfo?,
    override var timezone: Int?,
    override var name: String,
    override var cod: Int?
) : ForecastMainInfoEntity

data class WeatherEntityMockImpl(
    override var parentId: String,
    override var weatherId: Int?,
    override var main: String,
    override var description: String,
    override var icon: String
) : WeatherEntity

data class CoordInfoEntityMockImpl(
    override val lon: Float?,
    override val lat: Float?
) : CoordInfoEntity

data class MainInfoEntityMockImpl(
    override var temp: Float?,
    override var feels_like: Float?,
    override var temp_min: Float?,
    override var temp_max: Float?,
    override var pressure: Float?,
    override var humidity: Float?
) : MainInfoEntity

data class WindInfoEntityMockImpl(
    override var speed: Float?,
    override var deg: Float?
) : WindInfoEntity

data class CloudsInfoEntityMockImpl (
    override var all: Int?
): CloudsInfoEntity

data class SysInfoMockImpl (
    override var type: Int?,
    override var id: Int?,
    override var country: String?,
    override var sunrise: Int?,
    override var sunset: Int?
): SysInfo