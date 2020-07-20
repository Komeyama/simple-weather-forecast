package com.komeyama.simple.weather.model

/**
 * This method get prefecture id from link.
 * e.g.)
 * from http://weather.livedoor.com/area/forecast/016010 to 016010
 */
fun linkToPrefectureId(link: String?): String {
    return link?.removePrefix("http://weather.livedoor.com/area/forecast/") ?: ""
}