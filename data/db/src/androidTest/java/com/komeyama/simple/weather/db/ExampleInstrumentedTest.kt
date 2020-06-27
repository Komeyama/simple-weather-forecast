package com.komeyama.simple.weather.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.komeyama.simple.weather.db.internal.CacheDatabase
import com.komeyama.simple.weather.db.internal.dao.DetailCopyrightDao
import com.komeyama.simple.weather.db.internal.entity.DetailCopyrightEntityImpl
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
    private lateinit var cacheDatabase: CacheDatabase

    @Before
    fun createCopyrightDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        cacheDatabase = Room.inMemoryDatabaseBuilder(
            context, CacheDatabase::class.java
        ).build()
        copyrightDao = cacheDatabase.detailCopyrightDao()
    }

    @After
    @Throws(IOException::class)
    fun closeCopyrightDb() {
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
                    "copyrightImage"
                )
            )
        )
        val detailCopyrightInfo = copyrightDao.detailCopyright()
        assertThat(detailCopyrightInfo[0].title, equalTo("copyrightTitle"))
        assertThat(detailCopyrightInfo[0].link, equalTo("copyrightLink"))
        assertThat(detailCopyrightInfo[0].image, equalTo("copyrightImage"))
    }
}