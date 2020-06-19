package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.api.internal.ApiModule
import com.komeyama.simple.weather.model.PrefectureIds
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
    fun api_area_correct_test() {
        assertEquals(PrefectureIds.HOKKAIDO.prefectureName, fetchForecastInfo(PrefectureIds.HOKKAIDO.id)?.location?.area)
    }

    @Test
    fun api_prefecture_correct_test() {
        assertEquals(PrefectureIds.AOMORI.prefectureName, fetchForecastInfo(PrefectureIds.AOMORI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.IWATE.prefectureName, fetchForecastInfo(PrefectureIds.IWATE.id)?.location?.prefecture)
        assertEquals(PrefectureIds.MIYAGI.prefectureName, fetchForecastInfo(PrefectureIds.MIYAGI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.AKITA.prefectureName, fetchForecastInfo(PrefectureIds.AKITA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.YAMAGATA.prefectureName, fetchForecastInfo(PrefectureIds.YAMAGATA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.FUKUSHIMA.prefectureName, fetchForecastInfo(PrefectureIds.FUKUSHIMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.IBARAKI.prefectureName, fetchForecastInfo(PrefectureIds.IBARAKI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.TOCHIGI.prefectureName, fetchForecastInfo(PrefectureIds.TOCHIGI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.GUNMA.prefectureName, fetchForecastInfo(PrefectureIds.GUNMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.SAITAMA.prefectureName, fetchForecastInfo(PrefectureIds.SAITAMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.CHIBA.prefectureName, fetchForecastInfo(PrefectureIds.CHIBA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.TOKYO.prefectureName, fetchForecastInfo(PrefectureIds.TOKYO.id)?.location?.prefecture)
        assertEquals(PrefectureIds.KANAGAWA.prefectureName, fetchForecastInfo(PrefectureIds.KANAGAWA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.NIIGATA.prefectureName, fetchForecastInfo(PrefectureIds.NIIGATA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.TOYAMA.prefectureName, fetchForecastInfo(PrefectureIds.TOYAMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.ISHIKAWA.prefectureName, fetchForecastInfo(PrefectureIds.ISHIKAWA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.FUKUI.prefectureName, fetchForecastInfo(PrefectureIds.FUKUI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.YAMANASHI.prefectureName, fetchForecastInfo(PrefectureIds.YAMANASHI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.NAGANO.prefectureName, fetchForecastInfo(PrefectureIds.NAGANO.id)?.location?.prefecture)
        assertEquals(PrefectureIds.GIFU.prefectureName, fetchForecastInfo(PrefectureIds.GIFU.id)?.location?.prefecture)
        assertEquals(PrefectureIds.SHIZUOKA.prefectureName, fetchForecastInfo(PrefectureIds.SHIZUOKA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.AICHI.prefectureName, fetchForecastInfo(PrefectureIds.AICHI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.MIE.prefectureName, fetchForecastInfo(PrefectureIds.MIE.id)?.location?.prefecture)
        assertEquals(PrefectureIds.SHIGA.prefectureName, fetchForecastInfo(PrefectureIds.SHIGA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.KYOTO.prefectureName, fetchForecastInfo(PrefectureIds.KYOTO.id)?.location?.prefecture)
        assertEquals(PrefectureIds.OSAKA.prefectureName, fetchForecastInfo(PrefectureIds.OSAKA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.HYOUGO.prefectureName, fetchForecastInfo(PrefectureIds.HYOUGO.id)?.location?.prefecture)
        assertEquals(PrefectureIds.NARA.prefectureName, fetchForecastInfo(PrefectureIds.NARA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.WAKAYAMA.prefectureName, fetchForecastInfo(PrefectureIds.WAKAYAMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.TOTTORI.prefectureName, fetchForecastInfo(PrefectureIds.TOTTORI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.SHIMANANE.prefectureName, fetchForecastInfo(PrefectureIds.SHIMANANE.id)?.location?.prefecture)
        assertEquals(PrefectureIds.OKAYAMA.prefectureName, fetchForecastInfo(PrefectureIds.OKAYAMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.HIROSHIMA.prefectureName, fetchForecastInfo(PrefectureIds.HIROSHIMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.YAMAGUCHI.prefectureName, fetchForecastInfo(PrefectureIds.YAMAGUCHI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.TOKUSHIMA.prefectureName, fetchForecastInfo(PrefectureIds.TOKUSHIMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.KAGAWA.prefectureName, fetchForecastInfo(PrefectureIds.KAGAWA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.EHIME.prefectureName, fetchForecastInfo(PrefectureIds.EHIME.id)?.location?.prefecture)
        assertEquals(PrefectureIds.KOUCHI.prefectureName, fetchForecastInfo(PrefectureIds.KOUCHI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.FUKUOKA.prefectureName, fetchForecastInfo(PrefectureIds.FUKUOKA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.SAGA.prefectureName, fetchForecastInfo(PrefectureIds.SAGA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.NAGASAKI.prefectureName, fetchForecastInfo(PrefectureIds.NAGASAKI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.KUMAMOTO.prefectureName, fetchForecastInfo(PrefectureIds.KUMAMOTO.id)?.location?.prefecture)
        assertEquals(PrefectureIds.OITA.prefectureName, fetchForecastInfo(PrefectureIds.OITA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.MIYAZAKI.prefectureName, fetchForecastInfo(PrefectureIds.MIYAZAKI.id)?.location?.prefecture)
        assertEquals(PrefectureIds.KAGOSHIMA.prefectureName, fetchForecastInfo(PrefectureIds.KAGOSHIMA.id)?.location?.prefecture)
        assertEquals(PrefectureIds.OKINAWA.prefectureName, fetchForecastInfo(PrefectureIds.OKINAWA.id)?.location?.prefecture)
    }

    @Test
    fun api_cityID_is_not_existence_test() {
        assertEquals(null, fetchForecastInfo("1300100"))
    }

    private fun fetchForecastInfo(cityId: String): com.komeyama.simple.weather.model.ForecastInfo? {
        val apiModule = ApiModule()
        val retrofit = apiModule.retrofit()
        return runBlocking {
            val response = apiModule.provideForecastApi(retrofit).getForecastInfo(cityId)
            response.body()
        }
    }
}