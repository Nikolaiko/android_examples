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
}