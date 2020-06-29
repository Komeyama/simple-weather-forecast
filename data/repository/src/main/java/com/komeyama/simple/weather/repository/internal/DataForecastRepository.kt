package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.ForecastInfoDatabase
import com.komeyama.simple.weather.db.ForecastInfoEntity
import com.komeyama.simple.weather.model.*
import com.komeyama.simple.weather.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor(
    private val forecastApi: ForecastApi,
    private val forecastInfoDatabase: ForecastInfoDatabase
) : ForecastRepository {

    override fun dummyFunc() {
//        val forecastInfo: ForecastInfo? = runBlocking {
//            forecastApi.getForecastInfo("410020").body()
//        }
//        Timber.d(
//            "dummyFunc:api: %s\n :db  %s",
//            forecastInfo.toString(),
//            forecastInfoDatabase.toString()
//        )
        val forecastInfo: ForecastInfo? = runBlocking {
            forecastApi.getAllForecastLists()
        }
        Timber.d("dummyFunc:api: %s\n", forecastInfo.toString())
    }

    override suspend fun dummyLoad() {
        val info: List<ForecastInfoEntity> = forecastInfoDatabase.forecastInfo()
        info.forEach { forecastInfo ->
            Timber.d(
                "dummyFunc:load 00:\n forecastId:%s\n title:%s\n link:%s\n publicTime:%s\n description:%s %s\n detailLocation:%s %s %s\n",
                forecastInfo.forecastInfoEntityImpl.forecastId,
                forecastInfo.forecastInfoEntityImpl.title,
                forecastInfo.forecastInfoEntityImpl.link,
                forecastInfo.forecastInfoEntityImpl.publicTime,
                forecastInfo.forecastInfoEntityImpl.description.text,
                forecastInfo.forecastInfoEntityImpl.description.publicTime,
                forecastInfo.forecastInfoEntityImpl.detailLocation.area,
                forecastInfo.forecastInfoEntityImpl.detailLocation.city,
                forecastInfo.forecastInfoEntityImpl.detailLocation.prefecture
            )
            forecastInfo.detailForecastEntityImpl.forEach { detailForecast ->
                Timber.d(
                    "dummyFunc:load 01:\n id:%s\n parentId:%s\n date:%s\n dateLabel:%s\n telop:%s \n image:%s %s %s %s %s\n temperature:%s\n",
                    detailForecast.id,
                    detailForecast.parentId,
                    detailForecast.date,
                    detailForecast.dateLabel,
                    detailForecast.telop,
                    detailForecast.image.title,
                    detailForecast.image.link,
                    detailForecast.image.url,
                    detailForecast.image.width,
                    detailForecast.image.height,
                    detailForecast.temperature
                )
            }
            forecastInfo.pinpointLocationEntityImpl.forEach {
                Timber.d("dummyFunc:load 02: %s\n", it.toString())
            }
            Timber.d(
                "dummyFunc:lad 03: %s %s %s %s %s\n",
                forecastInfo.copyright?.copyrightID,
                forecastInfo.copyright?.parentId,
                forecastInfo.copyright?.title,
                forecastInfo.copyright?.link,
                forecastInfo.copyright?.image
            )
        }
    }

    override suspend fun dummySave() {
        forecastInfoDatabase.save(
            MainResponse(
                //DetailForecastResponse("a1","b1","c1","d1","e1"),
                0,
                "a1",
                "b2",
                "c2",
                DetailDescriptionResponse("a3", "b3"),
                DetailLocationResponse("a4", "b4", "b4")
            ),
            listOf(
                DetailForecastResponse(
                    0,
                    0,
                    "aa1",
                    "aa2",
                    "aa3",
                    DetailImageResponse("aai1", "aai1", "aai1", "aai1", "aai1"),
                    "aa5"
                ),
                DetailForecastResponse(
                    1,
                    0,
                    "aaa1",
                    "aaa2",
                    "aaa3",
                    DetailImageResponse("aaai1", "aaai1", "aaai1", "aaai1", "aaai1"),
                    "aaa5"
                )
            ),
            listOf(
                PinpointLocationResponse(0, 0, "bb1", "bb2"),
                PinpointLocationResponse(1, 0, "bbb1", "bbb2")
            ),
            listOf(
                DetailCopyrightResponse(
                    0, 0, "title", "http://aaa",
                    DetailImageResponse("i1", "i1", "i1", "i1", "i1")
                )
            )
        )
    }

}

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun forecastRepository(impl: DataWeatherRepository): ForecastRepository
}