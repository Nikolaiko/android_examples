package arcadeTests.intro.edgeOfOceans

import groups.intro.EdgeOfTheOceanTasks
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EdgeOfTheOceanTasksTests {

    private val edgeOfTheOceanTasks = EdgeOfTheOceanTasks()

    @Test
    fun shapeArea2Test() {
        assertEquals(area2Value, edgeOfTheOceanTasks.shapeArea(2))
    }

    @Test
    fun shapeArea3Test() {
        assertEquals(area3Value, edgeOfTheOceanTasks.shapeArea(3))
    }

    @Test
    fun shapeArea5Test() {
        assertEquals(area5Value, edgeOfTheOceanTasks.shapeArea(5))
    }

    @Test
    fun consecutive2Test1() {
        assertEquals(consecutiveAnswer1, edgeOfTheOceanTasks.makeArrayConsecutive2(firstArray))
    }

    @Test
    fun consecutive2Test2() {
        assertEquals(consecutiveAnswer2, edgeOfTheOceanTasks.makeArrayConsecutive2(secondArray))
    }

    @Test
    fun consecutive2Test3() {
        assertEquals(consecutiveAnswer3, edgeOfTheOceanTasks.makeArrayConsecutive2(thirdArray))
    }

    @Test
    fun almostIncreasingSequenceTest1() {
        assertEquals(
            false,
            edgeOfTheOceanTasks.almostIncreasingSequence(almostIncreasingSequenceTestList1.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest2() {
        assertEquals(
            true,
            edgeOfTheOceanTasks.almostIncreasingSequence(almostIncreasingSequenceTestList2.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest3() {
        assertEquals(
            false,
            edgeOfTheOceanTasks.almostIncreasingSequence(almostIncreasingSequenceTestList3.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest4() {
        assertEquals(
            true,
            edgeOfTheOceanTasks.almostIncreasingSequence(almostIncreasingSequenceTestList4.toMutableList())
        )
    }

    @Test
    fun almostIncreasingSequenceTest5() {
        assertEquals(
            true,
            edgeOfTheOceanTasks.almostIncreasingSequence(almostIncreasingSequenceTestList5.toMutableList())
        )
    }

    @Test
    fun matrixElementsSumTest1() {
        assertEquals(
            9,
            edgeOfTheOceanTasks.matrixElementsSum(matrixElementsSumTest1)
        )
    }
}