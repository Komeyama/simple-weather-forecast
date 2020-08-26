package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.api.internal.ApiModule
import com.komeyama.simple.weather.model.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    /*
    @Test
    fun api_area_correct_test() {
        assertEquals(
            PrefectureIds.HOKKAIDO.prefectureName,
            fetchForecastInfo(PrefectureIds.HOKKAIDO.id)?.location?.area
        )
    }
    */


    @Test
    fun api_city_correct_test() {
        try {
            CityIds.values().forEach {
                when {
                    it.id == CityIds.OFUNATO.id -> {
                        assertThat(fetchForecastInfo(it.id)?.name, containsString("Ōfunato"))
                    }
                    it.id == CityIds.SHINJO.id -> {
                        assertThat(fetchForecastInfo(it.id)?.name, containsString("Shinjō"))
                    }
                    it.id == CityIds.OTAWARA.id -> {
                        assertThat(fetchForecastInfo(it.id)?.name ,containsString("Ōtawara"))
                    }
                    it.id == CityIds.OTSU.id -> {
                        assertThat(fetchForecastInfo(it.id)?.name ,containsString("Ōtsu"))
                    }
                    it.id == CityIds.SHOBARA.id -> {
                        assertThat(fetchForecastInfo(it.id)?.name ,containsString("Shōbara"))
                    }
                    it.id == CityIds.OITA.id -> {
                        assertThat(fetchForecastInfo(it.id)?.name ,containsString("Ōita"))
                    }
                    it.id != CityIds.KOUCHI.id -> {
                        assertThat(fetchForecastInfo(it.id)?.name, containsString(it.id))
                    }
                }
                assertEquals("JP", fetchForecastInfo(it.id)?.sys?.country)
            }
        } catch (e: Exception) {
            println(e)
            assertThat(e.message, containsString("404"))
        }
    }

    @Test
    fun api_detail_city_correct_test() {
        try {
            CityIds.values().forEach {
                when {
                    it.id == CityIds.OFUNATO.id -> {
                        assertThat(fetchDetailForecastInfo(it.id)?.city?.name, containsString("Ōfunato"))
                    }
                    it.id == CityIds.SHINJO.id -> {
                        assertThat(fetchDetailForecastInfo(it.id)?.city?.name, containsString("Shinjō"))
                    }
                    it.id == CityIds.OTAWARA.id -> {
                        assertThat(fetchDetailForecastInfo(it.id)?.city?.name ,containsString("Ōtawara"))
                    }
                    it.id == CityIds.OTSU.id -> {
                        assertThat(fetchDetailForecastInfo(it.id)?.city?.name ,containsString("Ōtsu"))
                    }
                    it.id == CityIds.SHOBARA.id -> {
                        assertThat(fetchDetailForecastInfo(it.id)?.city?.name ,containsString("Shōbara"))
                    }
                    it.id == CityIds.OITA.id -> {
                        assertThat(fetchDetailForecastInfo(it.id)?.city?.name ,containsString("Ōita"))
                    }
                    it.id != CityIds.KOUCHI.id -> {
                        assertThat(fetchDetailForecastInfo(it.id)?.city?.name , containsString(it.id))
                    }
                }
                assertEquals("JP", fetchDetailForecastInfo(it.id)?.city?.country)
            }
        } catch (e: Exception) {
            print(e)
            assertThat(e.message, containsString("404"))
        }

    }

    @Test
    fun api_weekly_forecast_list_size_correct_test() {
        try {
            assertEquals(8, fetchDailyForecastInfo(43.220327F,142.863474F)?.daily?.size)

            assertEquals(8, fetchDailyForecastInfo(35.6895F,139.6917F)?.daily?.size)

            assertEquals(8, fetchDailyForecastInfo(35.011564F,135.768149F)?.daily?.size)

            assertEquals(8, fetchDailyForecastInfo(33.590184F,130.401689F)?.daily?.size)

            assertEquals(8, fetchDailyForecastInfo(26.120191F,127.702501F)?.daily?.size)
        } catch (e: Exception) {
            print(e)
            assertThat(e.message, containsString("404"))
        }
    }

    @Test
    fun api_cityID_is_not_existence_test() {
        try {
            assertEquals(null, fetchForecastInfo("komeyama"))
        } catch (e: Exception) {
            assertThat(e.message, containsString("404"))
        }

    }

    private fun fetchForecastInfo(cityName: String): ForecastInfo? {
        val apiModule = ApiModule()
        val httpClient = apiModule.provideHttpClient()
        return runBlocking {
            apiModule.provideForecastApi(httpClient).getForecastListFromName(cityName)
        }
    }

    private fun fetchDetailForecastInfo(cityName: String): DetailForecastInfo? {
        val apiModule = ApiModule()
        val httpClient = apiModule.provideHttpClient()
        return runBlocking {
            apiModule.provideForecastApi(httpClient).getDetailForecastListFromName(cityName)
        }
    }

    private fun fetchDailyForecastInfo(lat: Float, lon: Float): WeeklyForecastInfo? {
        val apiModule = ApiModule()
        val httpClient = apiModule.provideHttpClient()
        return runBlocking {
            apiModule.provideForecastApi(httpClient).getWeeklyForecastFromLatLon(lat,lon)
        }
    }
}