package arcadeTests.islandOfKnoldgeTests

import groups.IslandOfKnowledge
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IslandOfKnowledgeTests {
    private val knowledge = IslandOfKnowledge()

    @Test
    fun areEquallyStrongTests() {
        assertEquals(true, knowledge.areEquallyStrong(10, 15, 15, 10))
        assertEquals(false, knowledge.areEquallyStrong(10, 15, 15, 8))
    }

    @Test
    fun arrayMaximalAdjacentDifferenceTests() {
        assertEquals(3, knowledge.arrayMaximalAdjacentDifference(mutableListOf(2, 4, 1, 0)))
        assertEquals(2, knowledge.arrayMaximalAdjacentDifference(mutableListOf(10, 11, 13)))
        assertEquals(30, knowledge.arrayMaximalAdjacentDifference(mutableListOf(-14, 15, -15)))
    }

    @Test
    fun isIPv4AddressTest() {
        assertTrue(knowledge.isIPv4Address("172.16.254.1"))
        assertFalse(knowledge.isIPv4Address("1.1.1.1a"))
    }

    @Test
    fun avoidObstaclesTest() {
        assertEquals(4, knowledge.avoidObstacles(mutableListOf(5, 3, 6, 7, 9)))
    }

    @Test
    fun boxBlurTests() {
        assertEquals(
            mutableListOf(
                mutableListOf(40, 30)
            ),
            knowledge.boxBlur(
                mutableListOf(
                    mutableListOf(36,0,18,9),
                    mutableListOf(27,54,9,0),
                    mutableListOf(81,63,72,45)
                )
            )
        )
    }

    @Test
    fun mineSweeperTest() {
        assertEquals(
            mutableListOf(
                mutableListOf(1, 2, 1),
                mutableListOf(2, 1, 1),
                mutableListOf(1, 1, 1)
            ),
            knowledge.minesweeper(
                mutableListOf(
                    mutableListOf(true, false, false),
                    mutableListOf(false, true, false),
                    mutableListOf(false, false, false)
                )
            )
        )
    }
}