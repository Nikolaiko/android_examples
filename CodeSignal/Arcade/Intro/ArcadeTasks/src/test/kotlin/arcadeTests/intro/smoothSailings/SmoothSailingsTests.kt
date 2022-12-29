package arcadeTests.intro.smoothSailings

import groups.intro.SmoothSailing
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

    @Test
    fun sortByHeightTests() {
        assertEquals(
            mutableListOf(-1, 150, 160, 170, -1, -1, 180, 190),
            sailing.sortByHeight(mutableListOf(-1, 150, 190, 170, -1, -1, 160, 180))
        )
        assertEquals(
            mutableListOf(-1, -1, -1, -1, -1),
            sailing.sortByHeight(mutableListOf(-1, -1, -1, -1, -1)),
        )
    }

    @Test
    fun reverseInParenthesesTests() {
        assertEquals("rab", sailing.reverseInParentheses("(bar)"))
        assertEquals("foorabbazmilb", sailing.reverseInParentheses("foo(bar)baz(blim)"))
        assertEquals("foobazrabblim", sailing.reverseInParentheses("foo(bar(baz))blim"))
    }

}