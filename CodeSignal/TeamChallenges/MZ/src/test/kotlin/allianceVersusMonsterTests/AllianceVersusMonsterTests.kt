package allianceVersusMonsterTests

import MZTasks
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AllianceVersusMonsterTests {

    private val tasks = MZTasks()

    @Test
    fun basicTest() {
        assertEquals(basicTestResult, tasks.allianceVersusMonster(basicTestHealth, basicTestAttack))
    }

    @Test
    fun timeTest() {
        assertEquals(timeTestResult, tasks.allianceVersusMonster(timeTestHealth, timeTestAttack))
    }

    @Test
    fun orderTest() {
        assertEquals(orderTestResult, tasks.allianceVersusMonster(orderTestHealth, orderTestAttack))
    }

    @Test
    fun exactDivisionTest() {
        assertEquals(exactDivisionTestResult, tasks.allianceVersusMonster(exactDivisionTestHealth, exactDivisionTestAttack))
    }
}