package arcadeTests.intro.divingDeeper

import groups.intro.DivingDeeper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DivingDeeperTests {
    private val deeper = DivingDeeper()

    @Test
    fun extractEachKth() {
        assertEquals(
            mutableListOf(1, 2, 4, 5, 7, 8, 10),
            deeper.extractEachKth(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3)
        )

        assertEquals(
            mutableListOf(1, 2, 1, 2, 1, 2, 1, 2),
            deeper.extractEachKth(mutableListOf(1, 2, 1, 2, 1, 2, 1, 2), 10)
        )

        assertEquals(
            mutableListOf(),
            deeper.extractEachKth(mutableListOf(1, 1, 1, 1, 1), 1)
        )

        assertEquals(
            mutableListOf(1, 1, 1, 1),
            deeper.extractEachKth(mutableListOf(1, 2, 1, 2, 1, 2, 1, 2), 2)
        )
    }

    @Test
    fun firstDigitTests() {
        assertEquals('0', deeper.firstDigit("ok0"))
        assertEquals('9', deeper.firstDigit("a a_933"))
    }

    @Test
    fun differentSymbolsNaiveTests() {
        assertEquals(3, deeper.differentSymbolsNaive("cabca"))
        assertEquals(2, deeper.differentSymbolsNaive("aba"))
        assertEquals(10, deeper.differentSymbolsNaive("codesignal"))
    }

    @Test
    fun arrayMaxConsecutiveSumTests() {
        assertEquals(8, deeper.arrayMaxConsecutiveSum(mutableListOf(2, 3, 5, 1, 6),2))
        assertEquals(14, deeper.arrayMaxConsecutiveSum(mutableListOf(2, 4, 10, 1),2))
        assertEquals(
            14232,
            deeper.arrayMaxConsecutiveSum(
                mutableListOf(963, 741, 22, 851, 399, 382, 190, 247, 494, 452, 891, 532, 795, 920, 465, 831, 344, 391, 582, 897, 763, 951, 735, 806, 320, 702, 200, 59, 870, 345, 695, 321, 817, 83, 416, 36, 914, 335, 117, 985, 690, 303, 875, 556, 292, 309, 496, 791, 509, 360, 235, 784, 607, 341),
                23
            )
        )
    }
}