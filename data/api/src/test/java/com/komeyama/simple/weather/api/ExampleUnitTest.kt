package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.api.internal.ApiModule
import com.komeyama.simple.weather.model.CityIds
import com.komeyama.simple.weather.model.ForecastInfo
import com.komeyama.simple.weather.model.PrefectureIds
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.startsWith
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
}