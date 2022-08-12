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
}