package allianceHelpTests

import MZTasks
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AllianceHelpTests {

    private val tasks = MZTasks()

    @Test
    fun basicTest() {
        assertEquals(basicTestResult, tasks.allianceHelp(basicTestTime, basicTestSize))
    }

    @Test
    fun advancedTest() {
        assertEquals(advTestResult, tasks.allianceHelp(advTestTime, advTestSize))
    }
}