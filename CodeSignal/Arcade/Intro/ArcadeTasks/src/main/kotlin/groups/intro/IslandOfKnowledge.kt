package groups.intro

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

class IslandOfKnowledge {
    fun areEquallyStrong(yourLeft: Int, yourRight: Int, friendsLeft: Int, friendsRight: Int): Boolean {
        val minFriend = min(friendsLeft, friendsRight)
        val maxFriend = max(friendsLeft, friendsRight)

        val minYour = min(yourLeft, yourRight)
        val maxYour = max(yourLeft, yourRight)

        return minFriend == minYour && maxFriend == maxYour
    }

    fun arrayMaximalAdjacentDifference(inputArray: MutableList<Int>): Int {
        var maximumAdjacent = 0
        for (index in 0..inputArray.size - 2) {
            val currentDifference = abs(inputArray[index] - inputArray[index + 1])
            if (currentDifference > maximumAdjacent) {
                maximumAdjacent = currentDifference
            }
        }
        return maximumAdjacent
    }

    fun isIPv4Address(inputString: String): Boolean {
        val parts = inputString.split(".")
        if (parts.size != 4) return false

        parts.forEach {
            val integer = it.toIntOrNull() ?: return false
            if (integer > 255 || integer < 0) return false
            if (integer < 10 && it.length > 1) return false
        }
        return true
    }

    fun avoidObstacles(inputArray: MutableList<Int>): Int =
        (1..Int.MAX_VALUE).first { jump -> inputArray.all { it % jump != 0 } }

    fun boxBlur(image: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val minRowIndex = 1
        val minColumnIndex = 1

        val maxRowIndex = image.first().size - 2
        val maxColumnIndex = image.size - 2

        val resultBlur = mutableListOf<MutableList<Int>>()

        for (column in minColumnIndex..maxColumnIndex) {
            val rowList = mutableListOf<Int>()
            for (row in minRowIndex..maxRowIndex) {
                var decent = image[column][row] + image[column][row + 1] + image[column][row - 1]
                decent += image[column + 1][row]
                decent += image[column + 1][row + 1]
                decent += image[column + 1][row - 1]

                decent += image[column - 1][row]
                decent += image[column - 1][row + 1]
                decent += image[column - 1][row - 1]

                decent /= 9

                rowList.add(decent)
            }
            resultBlur.add(rowList)
        }
        return resultBlur
    }

    fun minesweeper(matrix: MutableList<MutableList<Boolean>>): MutableList<MutableList<Int>> {
        val totalPole = mutableListOf<MutableList<Int>>()
        for (column in matrix.indices) {
            val rowPole = mutableListOf<Int>()
            for (row in matrix[column].indices) {
                var mines = try {
                    if (matrix[column][row - 1]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                mines += try {
                    if (matrix[column][row + 1]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                mines += try {
                    if (matrix[column + 1][row]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                mines += try {
                    if (matrix[column - 1][row]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                mines += try {
                    if (matrix[column + 1][row - 1]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                mines += try {
                    if (matrix[column + 1][row + 1]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                mines += try {
                    if (matrix[column - 1][row - 1]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                mines += try {
                    if (matrix[column - 1][row + 1]) 1 else 0
                } catch (exception: java.lang.IndexOutOfBoundsException) { 0 }

                rowPole.add(mines)
            }
            totalPole.add(rowPole)
        }
        return totalPole
    }
}