package com.blank.chapter10.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.blank.chapter10.data.local.db.AppDatabase
import com.blank.chapter10.data.local.db.dao.UsersDao
import com.blank.chapter10.data.local.db.entity.User
import com.blank.chapter10.helper.getValueBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DBTest {

    @get:Rule
    val instanTaskExecutorRule = InstantTaskExecutorRule()

    private var dao: UsersDao? = null
    private var db: AppDatabase? = null

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        dao = db?.usersDao()
    }

    @Test
    @Throws(Exception::class)
    fun `insert_and_get_data_from_db_room`() {
        val user = User(1, "ghozi@gmail.com", "ghozi")

        // insert
        val inserUser = dao?.insertUser(user)
        Assert.assertNotNull(inserUser)

        //get user
        val inserted = dao?.getUsers()
        Assert.assertNotNull(inserted)

        val userValue = inserted?.getValueBlocking()
        Assert.assertEquals(user.email, userValue?.email)
        Assert.assertNotNull(userValue)
    }

    @After
    fun closeDb() {
        db?.close()
    }
}