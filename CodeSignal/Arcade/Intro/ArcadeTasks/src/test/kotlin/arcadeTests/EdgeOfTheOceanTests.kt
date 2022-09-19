package arcadeTests

import EdgeOfTheOcean
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EdgeOfTheOceanTests {

    private val edgeOfTheOcean = EdgeOfTheOcean()

    @Test
    fun shapeArea2Test() {
        assertEquals(area2Value, edgeOfTheOcean.shapeArea(2))
    }

    @Test
    fun shapeArea3Test() {
        assertEquals(area3Value, edgeOfTheOcean.shapeArea(3))
    }

    @Test
    fun shapeArea5Test() {
        assertEquals(area5Value, edgeOfTheOcean.shapeArea(5))
    }

    @Test
    fun consecutive2Test1() {
        assertEquals(consecutiveAnswer1, edgeOfTheOcean.makeArrayConsecutive2(firstArray))
    }

    @Test
    fun consecutive2Test2() {
        assertEquals(consecutiveAnswer2, edgeOfTheOcean.makeArrayConsecutive2(secondArray))
    }

    @Test
    fun consecutive2Test3() {
        assertEquals(consecutiveAnswer3, edgeOfTheOcean.makeArrayConsecutive2(thirdArray))
    }

    @Test
    fun almostIncreasingSequenceTest1() {
        assertEquals(
            false,
            edgeOfTheOcean.almostIncreasingSequence(almostIncreasingSequenceTestList1.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest2() {
        assertEquals(
            true,
            edgeOfTheOcean.almostIncreasingSequence(almostIncreasingSequenceTestList2.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest3() {
        assertEquals(
            false,
            edgeOfTheOcean.almostIncreasingSequence(almostIncreasingSequenceTestList3.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest4() {
        assertEquals(
            true,
            edgeOfTheOcean.almostIncreasingSequence(almostIncreasingSequenceTestList4.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest5() {
        assertEquals(
            true,
            edgeOfTheOcean.almostIncreasingSequence(almostIncreasingSequenceTestList5.toMutableList())
        )
    }
}