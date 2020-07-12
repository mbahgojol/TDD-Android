package com.blank.chapter10.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class UtilityTest {

    @Test
    fun getSum() {
        val mockUtil = Mockito.mock(Utility::class.java)
        Mockito.`when`(mockUtil.getSum(2, 2)).thenReturn(4)
        val actualResult = mockUtil.getSum(2, 2)
        assertEquals(4, actualResult)
    }
}