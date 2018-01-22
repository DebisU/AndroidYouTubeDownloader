package com.debisu.youtubedownloader

import com.commit451.youtubeextractor.YouTubeExtractor
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testExtraction() {
        val GRID_YOUTUBE_ID = "https://www.youtube.com/watch?v=5G7mPpiL0KU"
        val extractor = YouTubeExtractor.create()
        val result = extractor.extract(GRID_YOUTUBE_ID)
                .blockingGet()
        Assert.assertNotNull(result)
        Assert.assertTrue(result.videoStreams.isNotEmpty())
    }
}
