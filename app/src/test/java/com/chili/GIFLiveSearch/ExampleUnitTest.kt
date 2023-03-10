package com.chili.GIFLiveSearch

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Simple unit tests to check if function works properly
 */
class ExampleUnitTest {
    @Test
    fun titleRemoveGIFnBy() {
        assertEquals("Double Down Cheeseburger", TitleProcessing.removeGIFnBy("Double Down Cheeseburger GIF by biba by bobaasdasd "))
    }
    @Test
    fun titleRemoveBy() {
        assertEquals("Communicate Better Daily", TitleProcessing.removeGIFnBy("Communicate Better Daily by biba by bobaasdasd "))
    }
    @Test
    fun titleRemoveGIF() {
        assertEquals("Arch Deluxe Burger", TitleProcessing.removeGIFnBy("Arch Deluxe Burger GIF GIF"))
    }
    @Test
    fun titleRemoveNothing() {
        assertEquals("Arch Deluxe Burger", TitleProcessing.removeGIFnBy("Arch Deluxe Burger"))
    }
    @Test
    fun titleGetAuthor() {
        assertEquals("biba by bobaasdasd", TitleProcessing.getAuthorFrom("Double Down Cheeseburger GIF by biba by bobaasdasd"))
    }
    @Test
    fun titleGetNothing() {
        assertEquals("unknown", TitleProcessing.getAuthorFrom("Double Down Cheeseburger GIF "))
    }
}