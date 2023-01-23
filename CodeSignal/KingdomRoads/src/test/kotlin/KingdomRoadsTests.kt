import graphs.KingdomRoads
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KingdomRoadsTests {

    private val roads = KingdomRoads()

    @Test
    fun newRoadSystemTests() {
        assertEquals(
            true,
            roads.newRoadSystem(
                mutableListOf(
                    mutableListOf(false, true,  false, false),
                    mutableListOf(false, false, true,  false),
                    mutableListOf(true,  false, false, true),
                    mutableListOf(false, false, true,  false)
                )
            )
        )
    }
}