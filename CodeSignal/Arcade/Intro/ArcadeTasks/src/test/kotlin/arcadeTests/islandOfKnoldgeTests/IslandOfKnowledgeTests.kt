package arcadeTests.islandOfKnoldgeTests

import groups.IslandOfKnowledge
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
}