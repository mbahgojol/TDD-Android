package com.blank.chapter10

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class MainViewModelTest {

    private lateinit var viewmodel: MainViewModel

    @Before
    fun setup() {
        viewmodel = MainViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `menambah counter punya saya`() {
        viewmodel.increment()
        println(viewmodel._count.value)

        assert(viewmodel._count.value!! > 0)
        //assert(viewmodel._count.value!! == 0) jikalau mau disalahin
    }

    @Test
    fun `mengurangi counter punya saya`() {
        viewmodel.decrement()
        println(viewmodel._count.value)

        assert(viewmodel._count.value!! < 0)
        //assert(viewmodel._count.value!! == 0) jikalau mau disalahin
    }
}