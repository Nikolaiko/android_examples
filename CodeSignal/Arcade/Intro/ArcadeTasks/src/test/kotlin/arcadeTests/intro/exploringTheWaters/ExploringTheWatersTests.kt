package arcadeTests.intro.exploringTheWaters

import groups.intro.ExploringTheWaters
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    @Test
    fun testAreSimilar() {
        assertEquals(true, exploring.areSimilars(mutableListOf(1, 2, 3), mutableListOf(2, 1, 3)))
        assertEquals(false, exploring.areSimilars(mutableListOf(1, 2, 2), mutableListOf(2, 1, 1)))
        assertEquals(
            true,
            exploring.areSimilars(mutableListOf(1, 2, 1, 2, 2, 1), mutableListOf(2, 2, 1, 1, 2, 1))
        )
        assertEquals(
            false,
            exploring.areSimilars(mutableListOf(1, 1, 4), mutableListOf(1, 2, 3))
        )
    }

    @Test
    fun arrayChangeTest() {
        assertEquals(12, exploring.arrayChange(mutableListOf(2, 1, 10, 1)))
        assertEquals(3, exploring.arrayChange(mutableListOf(1, 1, 1)))
        assertEquals(13, exploring.arrayChange(mutableListOf(2, 3, 3, 5, 5, 5, 4, 12, 12, 10, 15)))
    }

    @Test
    fun testPalindromeRearranging() {
        assertTrue(exploring.palindromeRearranging("aabb"))
        assertTrue(exploring.palindromeRearranging("zaa"))
        assertFalse(exploring.palindromeRearranging("abcad"))
    }
}