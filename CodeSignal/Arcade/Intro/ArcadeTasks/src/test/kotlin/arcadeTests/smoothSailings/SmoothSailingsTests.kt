package arcadeTests.smoothSailings

import groups.SmoothSailing
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SmoothSailingsTests {

    private val sailing = SmoothSailing()

    @Test
    fun testAllLongestStrings() {
        val testStrings = mutableListOf(
            "enyky",
            "benyky",
            "yely",
            "varennyky"
        )

        val result = sailing.allLongestStrings(testStrings)
        assertEquals(result.size, 1)
        assertTrue(result.contains("varennyky"))
    }

    @Test
    fun commonCharacterCountTests() {
        val s1 = "aabcc"
        val s2 = "adcaa"

        assertEquals(sailing.commonCharacterCount(s1, s2), 3)
    }

    @Test
    fun isLuckyTests() {
        assertTrue(sailing.isLucky(1230))
        assertFalse(sailing.isLucky(1235))
    }
}