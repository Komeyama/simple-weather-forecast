package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.ForecastInfoDatabase
import com.komeyama.simple.weather.db.ForecastInfoEntity
import com.komeyama.simple.weather.db.PinpointLocationEntity
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
        runBlocking {
            forecastInfoDatabase.save(forecastInfo)
        }
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
                forecastInfo.forecastInfoEntityImpl.description?.text,
                forecastInfo.forecastInfoEntityImpl.description?.publicTime,
                forecastInfo.forecastInfoEntityImpl.detailLocation?.area,
                forecastInfo.forecastInfoEntityImpl.detailLocation?.city,
                forecastInfo.forecastInfoEntityImpl.detailLocation?.prefecture
            )
            forecastInfo.detailForecastEntityImpl.forEach { detailForecast ->
                Timber.d(
                    "dummyFunc:load 01:\n id:%s\n parentId:%s\n date:%s\n dateLabel:%s\n telop:%s \n image:%s %s %s %s\n temperature:%s\n",
                    detailForecast.id,
                    detailForecast.parentId,
                    detailForecast.date,
                    detailForecast.dateLabel,
                    detailForecast.telop,
                    detailForecast.image?.title,
                    detailForecast.image?.url,
                    detailForecast.image?.width,
                    detailForecast.image?.height,
                    detailForecast.temperature
                )
            }
            forecastInfo.pinpointLocationEntityImpl.forEach {
                Timber.d("dummyFunc:load 02: %s\n", it.toString())
            }
            Timber.d(
                "dummyFunc:lad 03:%s\n %s\n %s\n %s\n %s\n %s\n",
                forecastInfo.detailCopyrightEntity.detailCopyrightMainEntity.id,
                forecastInfo.detailCopyrightEntity.detailCopyrightMainEntity.parentId,
                forecastInfo.detailCopyrightEntity.detailCopyrightMainEntity.title,
                forecastInfo.detailCopyrightEntity.detailCopyrightMainEntity.link,
                forecastInfo.detailCopyrightEntity.detailCopyrightMainEntity.image,
                forecastInfo.detailCopyrightEntity.pinpointLocationOfCopyEntityImpl
            )
        }

    }

    override suspend fun dummySave() {
        forecastInfoDatabase.save(
            forecastInfo = ForecastInfo(
                location = DetailLocation(
                    area = "area01",
                    prefecture = "prefecture01",
                    city = "city01"
                ),
                title = "title01",
                link = "link01",
                publicTime = "publicTime01",
                description = DetailDescription(
                    text = "desc_text01",
                    publicTime = "desc_publicTime01"
                ),
                forecasts = listOf(
                    DetailForecasts(
                        date = "forecasts_data01",
                        dateLabel = "forecasts_data_label01",
                        telop = "forecasts_data_telop",
                        image = DetailImage(
                            title = "image_title01",
                            url = "image_url01",
                            width = "image_widt01",
                            height = "image_height01"
                        ),
                        temperature = Temperature(
                            min = DetailTemperature(
                                celsius = "celsius",
                                fahrenheit = "fahrenheit"
                            ),
                            max = DetailTemperature(
                                celsius = "celsius",
                                fahrenheit = "fahrenheit"
                            )
                        )
                    )
                ),
                pinpointLocations = listOf(
                    PinpointLocation(
                        link = "pinpoint_link01",
                        name = "pinpoint_name01"
                    )
                ),
                copyright = DetailCopyright(
                    title = "detail_copyright_title",
                    link = "detail_copyright_link",
                    image = DetailImage(
                        title = "image_cp_title01",
                        url = "image_cp_url01",
                        width = "image_cp_width01",
                        height = "image_cp_height01"
                    ),
                    provider = listOf(
                        PinpointLocation(
                            link = "pinpoint_cp_link01",
                            name = "pinpoint_cp_name01"
                        )
                    )
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