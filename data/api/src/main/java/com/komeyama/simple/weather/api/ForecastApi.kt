package com.komeyama.simple.weather.api

import com.google.gson.GsonBuilder
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

//class ForecastApi {
//    private val retrofit: Retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
//            .baseUrl("http://weather.livedoor.com/")
//            .build()
//    val service: IGetForecastInfo = retrofit.create(IGetForecastInfo::class.java)
//}
//
interface IGetForecastInfo {
    @GET("forecast/webservice/json/v1")
    fun getRepos(@Query("city") userID: String): Call<ForecastInfo>
}