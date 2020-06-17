package com.komeyama.simple.weather.api

import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun api_test() {
        val forecastInfo = fetchForecastInfo()
        Thread.sleep(1000)
        print(forecastInfo)
    }

    private fun fetchForecastInfo(): ForecastInfo? {

        val call = ForecastApi().service.getRepos("410020")
        var forecastInfo: ForecastInfo? = null
        call.enqueue(object : Callback<ForecastInfo> {
            override fun onResponse(call: Call<ForecastInfo>?, response: Response<ForecastInfo>?) {
                try{
                    forecastInfo = response?.body()
                }catch (e: IOException){}
            }

            override fun onFailure(call: Call<ForecastInfo>?, t: Throwable?) {
                print(t)
            }
        })
        Thread.sleep(1000)
        return forecastInfo
    }
}