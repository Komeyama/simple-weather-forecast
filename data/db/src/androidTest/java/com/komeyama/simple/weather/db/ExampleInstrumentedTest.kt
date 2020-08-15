package com.komeyama.simple.weather.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.komeyama.simple.weather.db.internal.CacheDatabase
import com.komeyama.simple.weather.db.internal.dao.*
import com.komeyama.simple.weather.db.internal.dao.old.DetailCopyrightMainDao
import com.komeyama.simple.weather.db.internal.dao.old.DetailImageDao
import com.komeyama.simple.weather.db.internal.dao.old.PinpointLocationDao
import com.komeyama.simple.weather.db.internal.entity.*
import com.komeyama.simple.weather.db.internal.entity.old.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var favoritePlaceDao: FavoritePlaceDao
    private lateinit var forecastMainInfoDao: ForecastMainInfoDao
    private lateinit var copyrightMainDao: DetailCopyrightMainDao
    private lateinit var imageDao: DetailImageDao
    private lateinit var pinpointLocationDao: PinpointLocationDao
    private lateinit var cacheDatabase: CacheDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        cacheDatabase = Room.inMemoryDatabaseBuilder(
            context, CacheDatabase::class.java
        ).build()
        favoritePlaceDao = cacheDatabase.favoritePlaceDao()
        forecastMainInfoDao = cacheDatabase.forecastMainInfoDao()
        copyrightMainDao = cacheDatabase.detailCopyrightMainDao()
        imageDao = cacheDatabase.detailImageDao()
        pinpointLocationDao = cacheDatabase.pinpointLocationDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        cacheDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndDeleteFavoritePlaceAndReadInList() {
        // write
        favoritePlaceDao.insert(FavoritePlaceEntityImpl(forecastId = "100001"))
        favoritePlaceDao.insert(FavoritePlaceEntityImpl(forecastId = "100002"))
        favoritePlaceDao.insert(FavoritePlaceEntityImpl(forecastId = "100010"))

        // read
        var favoritePlaceInfo = favoritePlaceDao.favoritePlaceInfo()
        assertThat(favoritePlaceInfo[0].forecastId, equalTo("100001"))
        assertThat(favoritePlaceInfo[1].forecastId, equalTo("100002"))
        assertThat(favoritePlaceInfo[2].forecastId, equalTo("100010"))

        // delete
        favoritePlaceDao.delete("100002")

        // re-read
        favoritePlaceInfo = favoritePlaceDao.favoritePlaceInfo()
        assertThat(favoritePlaceInfo.size, equalTo(2))
        assertThat(favoritePlaceInfo[0].forecastId, equalTo("100001"))
        assertThat(favoritePlaceInfo[1].forecastId, equalTo("100010"))
    }


    @Test
    @Throws(Exception::class)
    fun writeCopyrightMainAndReadInList() {
        insertCopyrightMainData()
        val detailCopyrightMainInfo = copyrightMainDao.detailCopyrightMainInfo()
        assertThat(detailCopyrightMainInfo[0].title, equalTo("copyrightTitle"))
        assertThat(detailCopyrightMainInfo[0].link, equalTo("copyrightLink"))
        assertThat(detailCopyrightMainInfo[0].image?.title, equalTo("imageTitle"))
        assertThat(detailCopyrightMainInfo[0].image?.url, equalTo("imageUrl"))
        assertThat(detailCopyrightMainInfo[0].image?.width, equalTo("imageWidth"))
        assertThat(detailCopyrightMainInfo[0].image?.height, equalTo("imageHeight"))
    }

    @Test
    @Throws(Exception::class)
    fun writeImageAndReadInList() {
        insertImageData()
        val detailImageInfo = imageDao.detailImageInfo()
        assertThat(detailImageInfo[0].title, equalTo("imageTitle"))
        assertThat(detailImageInfo[0].url, equalTo("imageUrl"))
        assertThat(detailImageInfo[0].width, equalTo("imageWidth"))
        assertThat(detailImageInfo[0].height, equalTo("imageHeight"))
    }

    private fun insertCopyrightMainData() {
        forecastMainInfoDao.insert(
                ForecastMainInfoEntityImpl(
                    forecastId = 0,
                    title = "forecast title",
                    link = "forecast_link",
                    publicTime = "public time",
                    detailLocation = DetailLocationEntityImpl(
                        area = "detail area",
                        prefecture = "detail prefecture",
                        city = "detail city"
                    ),
                    description = DetailDescriptionEntityImpl(
                        text = "detail text",
                        publicTime = "detail public time"
                    )
                )
            )


        copyrightMainDao.insert(
            DetailCopyrightMainEntityImpl(
                0, 0,
                "copyrightTitle",
                "copyrightLink",
                DetailImageEntityImplOfCopyright(
                    title = "imageTitle",
                    url = "imageUrl",
                    width = "imageWidth",
                    height = "imageHeight"
                )
            )
        )
    }

    private fun insertImageData() {
        imageDao.insert(
            listOf(
                DetailImageEntityImpl(
                    title = "imageTitle",
                    url = "imageUrl",
                    width = "imageWidth",
                    height = "imageHeight"
                )
            )
        )
    }

}