package com.komeyama.simple.weather.api

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun api_city_correct_test() {
        assertEquals("東京", fetchForecastInfo("130010")?.location?.city)
        assertEquals("静岡", fetchForecastInfo("220010")?.location?.city)
    }

    @Test
    fun api_cityID_is_not_existence_test() {
        assertEquals(null, fetchForecastInfo("1300100"))
    }

    private fun fetchForecastInfo(cityId: String): com.komeyama.simple.weather.model.ForecastInfo? {
        val apiModule = ApiModule()
        val retrofit = apiModule.retrofit()
        return runBlocking {
            val response = apiModule.provideIGetForecastInfo(retrofit).getForecastInfo(cityId)
            response.body()
        }
    }
}