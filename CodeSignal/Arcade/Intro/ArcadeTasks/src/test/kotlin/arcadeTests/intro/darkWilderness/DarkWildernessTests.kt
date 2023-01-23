package arcadeTests.intro.darkWilderness

import groups.intro.DarkWilderness
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DarkWildernessTests {

    private val dark = DarkWilderness()

    @Test
    fun growingPlantTests() {
        assertEquals(
            10,
            dark.growingPlant(100, 10, 910)
        )

        assertEquals(
            5,
            dark.growingPlant(6, 5, 10)
        )
    }


}