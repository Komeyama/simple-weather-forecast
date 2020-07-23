package com.komeyama.simple.weather.model

/**
 * This method get forecast id id from link.
 * e.g.)
 * from http://weather.livedoor.com/area/forecast/016010 to 016010
 */
fun linkToForecastId(link: String?): String {
    return link?.removePrefix("http://weather.livedoor.com/area/forecast/") ?: ""
}