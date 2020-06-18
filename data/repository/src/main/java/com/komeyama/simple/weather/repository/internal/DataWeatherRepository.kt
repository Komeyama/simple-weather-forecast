package com.komeyama.simple.weather.repository.internal

import android.util.Log
import com.komeyama.simple.weather.api.ForecastInfo
import com.komeyama.simple.weather.api.ForecastService
import dagger.Binds
import dagger.Module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor(
        private val forecastService: ForecastService
) : WeatherRepository {

    override fun dummyFunc() {
        Log.d("WeatherRepository dummy", "dummy!!")
        val call = forecastService.getForecastInfo("410020")
        call.enqueue(object : Callback<ForecastInfo> {
            override fun onResponse(call: Call<ForecastInfo>?, response: Response<ForecastInfo>?) {
                try {
                    Log.d("WeatherRepository dummy:ok ", response?.body().toString())
                } catch (e: IOException) {
                    Log.d("WeatherRepository dummy:e ", e.toString())
                }
            }

            override fun onFailure(call: Call<ForecastInfo>?, t: Throwable?) {
                Log.d("WeatherRepository dummy:ng ", t.toString())
            }
        })
    }
}

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun weatherRepository(impl: DataWeatherRepository): WeatherRepository
}