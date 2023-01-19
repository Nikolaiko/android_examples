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
}