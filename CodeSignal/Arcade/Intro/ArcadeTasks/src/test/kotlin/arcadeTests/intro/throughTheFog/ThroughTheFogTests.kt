package arcadeTests.intro.throughTheFog

import groups.intro.SmoothSailing
import groups.intro.ThroughTheFog
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ThroughTheFogTests {
    private val fog = ThroughTheFog()

    @Test
    fun circleOfNumbersTests() {
        assertEquals(7, fog.circleOfNumbers(10, 2))
        assertEquals(3, fog.circleOfNumbers(4, 1))
    }

    @Test
    fun depositProfitTests() {
        assertEquals(3, fog.depositProfit(100, 20, 170))
        assertEquals(6, fog.depositProfit(1, 100, 64))
    }

    @Test
    fun absoluteValuesSumMinimizationTests() {
        assertEquals(4, fog.absoluteValuesSumMinimization(mutableListOf(2, 4, 7)))
        assertEquals(2, fog.absoluteValuesSumMinimization(mutableListOf(2, 3)))
        assertEquals(0, fog.absoluteValuesSumMinimization(mutableListOf(
            -1000000, -10000, -10000, -1000, -100, -10, -1, 0, 1, 10, 100, 1000, 10000, 100000, 1000000
        )))
    }

    @Test
    fun stringsRearrangementTests() {
        assertEquals(false, fog.stringsRearrangement(mutableListOf("aba", "bbb", "bab")))
        assertEquals(true, fog.stringsRearrangement(mutableListOf("zzzzab", "zzzzbb", "zzzzaa")))
    }
}