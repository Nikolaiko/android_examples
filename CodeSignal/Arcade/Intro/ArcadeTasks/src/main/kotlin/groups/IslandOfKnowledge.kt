package groups

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
}