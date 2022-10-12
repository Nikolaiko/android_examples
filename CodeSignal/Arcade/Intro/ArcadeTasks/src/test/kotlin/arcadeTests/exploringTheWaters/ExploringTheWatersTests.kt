package arcadeTests.exploringTheWaters

import groups.ExploringTheWaters
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExploringTheWatersTests {
    private val exploring = ExploringTheWaters()

    @Test
    fun testAlternatingSums() {
        assertEquals(mutableListOf(180, 105), exploring.alternatingSums(mutableListOf(50, 60, 60, 45, 70)))
    }

    @Test
    fun testAddBorder() {
        assertEquals(mutableListOf(
            "*****",
            "*abc*",
            "*ded*",
            "*****"
        ), exploring.addBorder(
            mutableListOf(
                "abc",
                "ded"
            )
        ))
    }
}