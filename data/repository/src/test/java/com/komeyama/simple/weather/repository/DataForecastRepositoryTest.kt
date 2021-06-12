package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.repository.internal.DataForecastRepository
import com.komeyama.simple.weather.repository.internal.toForecastInfoList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.Before

class DataForecastRepositoryTest {

    private lateinit var dataForecastRepository: DataForecastRepository
    private val favoritePlaceDatabase = mockk<FavoritePlaceDatabase>(relaxed = true)
    private val forecastApi = mockk<ForecastApi>(relaxed = true)
    private val forecastInfoDatabase = mockk<ForecastInfoDatabase>(relaxed = true)
    private var mockResOfForecastContents: List<ForecastInfoEntityMockImpl> = listOf(
        ForecastInfoEntityMockImpl(
            ForecastMainInfoEntityMockImpl(
                id = 0,
                coord = CoordInfoEntityMockImpl(35.6809591f, 139.7673068f),
                base = "base",
                main = MainInfoEntityMockImpl(20f, 25f, 18f, 22f, 1000f, 20f),
                visibility = "visibility",
                wind = WindInfoEntityMockImpl(3f, 10f),
                clouds = CloudsInfoEntityMockImpl(10),
                sys = SysInfoMockImpl(10,100,"ja",sunrise = 0, sunset = 0),
                timezone = 100000,
                name = "rain",
                cod = 0
            ),
            listOf(WeatherEntityMockImpl(
                parentId = "parentId",
                weatherId = 1000,
                main = "rain",
                description = "Sunny after rain",
                icon = "https://weathermap/rain.png"
            ))
        )
    )

    @Before
    fun setUp() {
        dataForecastRepository =
            DataForecastRepository(favoritePlaceDatabase, forecastApi, forecastInfoDatabase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `weather data exist in db`() = runBlockingTest {
        coEvery {
            forecastInfoDatabase.forecastInfoFlow()
        } returns flowOf(
            mockResOfForecastContents
        )
        val response = dataForecastRepository.forecastContents()
        Assert.assertEquals(
            flowOf(mockResOfForecastContents.toForecastInfoList()).toList(),
            response.toList()
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `weather data does not exist in db`() = runBlockingTest {
        mockResOfForecastContents = listOf()
        coEvery {
            forecastInfoDatabase.forecastInfoFlow()
        } returns flowOf(mockResOfForecastContents)

        val response = dataForecastRepository.forecastContents()

        Assert.assertEquals(
            flowOf(mockResOfForecastContents.toForecastInfoList()).toList(),
            response.toList()
        )
    }
}