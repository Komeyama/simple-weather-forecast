package com.komeyama.simple.weather.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.komeyama.simple.weather.db.internal.CacheDatabase
import com.komeyama.simple.weather.db.internal.dao.DetailCopyrightDao
import com.komeyama.simple.weather.db.internal.dao.DetailImageDao
import com.komeyama.simple.weather.db.internal.entity.DetailCopyrightEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImpl
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
    private lateinit var copyrightDao: DetailCopyrightDao
    private lateinit var imageDao: DetailImageDao
    private lateinit var cacheDatabase: CacheDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        cacheDatabase = Room.inMemoryDatabaseBuilder(
            context, CacheDatabase::class.java
        ).build()
        copyrightDao = cacheDatabase.detailCopyrightDao()
        imageDao = cacheDatabase.detailImageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        cacheDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeCopyrightAndReadInList() {
        copyrightDao.insert(
            listOf(
                DetailCopyrightEntityImpl(
                    "copyrightTitle",
                    "copyrightLink",
                    DetailImageEntityImpl(
                        "imageTitle",
                        "imageLink",
                        "imageUrl",
                        "imageWidth",
                        "imageHeight"
                    )
                )
            )
        )
        val detailCopyrightInfo = copyrightDao.detailCopyright()
        assertThat(detailCopyrightInfo[0].title, equalTo("copyrightTitle"))
        assertThat(detailCopyrightInfo[0].link, equalTo("copyrightLink"))
        assertThat(detailCopyrightInfo[0].image.title, equalTo("imageTitle"))
        assertThat(detailCopyrightInfo[0].image.link, equalTo("imageLink"))
        assertThat(detailCopyrightInfo[0].image.url, equalTo("imageUrl"))
        assertThat(detailCopyrightInfo[0].image.width, equalTo("imageWidth"))
        assertThat(detailCopyrightInfo[0].image.height, equalTo("imageHeight"))
    }

    @Test
    @Throws(Exception::class)
    fun writeImageAndReadInList() {
        imageDao.insert(
            listOf(
                DetailImageEntityImpl(
                    "imageTitle",
                    "imageLink",
                    "imageUrl",
                    "imageWidth",
                    "imageHeight"
                )
            )
        )
        val detailImageInfo = imageDao.detailImageInfo()
        assertThat(detailImageInfo[0].title, equalTo("imageTitle"))
        assertThat(detailImageInfo[0].link, equalTo("imageLink"))
        assertThat(detailImageInfo[0].url, equalTo("imageUrl"))
        assertThat(detailImageInfo[0].width, equalTo("imageWidth"))
        assertThat(detailImageInfo[0].height, equalTo("imageHeight"))
    }
}