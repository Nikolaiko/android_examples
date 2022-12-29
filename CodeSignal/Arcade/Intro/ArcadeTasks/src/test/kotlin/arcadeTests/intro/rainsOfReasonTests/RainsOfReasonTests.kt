package arcadeTests.intro.rainsOfReasonTests

import groups.intro.RainsOfReason
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RainsOfReasonTests {
    private val rains = RainsOfReason()

    @Test
    fun arrayReplaceTest() {
        assertEquals(
            mutableListOf(3, 2, 3),
            rains.arrayReplace(mutableListOf(1, 2, 1), 1, 3)
        )
    }

    @Test
    fun evenDigitsOnlyTest() {
        assertTrue(rains.evenDigitsOnly(22444682))
        assertFalse(rains.evenDigitsOnly(2123444682))
    }

    @Test
    fun variableNameTest() {
        assertTrue(rains.variableName("var_1__Int"))
        assertTrue(rains.variableName("variable"))
        assertFalse(rains.variableName("a a_2"))
    }

    @Test
    fun alphabeticShiftTest() {
        assertEquals("dsbaz", rains.alphabeticShift("crazy"))
    }

    @Test
    fun chessBoardCellColorTest() {
        assertTrue(rains.chessBoardCellColor("A1", "C3"))
        assertFalse(rains.chessBoardCellColor("A1", "H1"))
    }
}