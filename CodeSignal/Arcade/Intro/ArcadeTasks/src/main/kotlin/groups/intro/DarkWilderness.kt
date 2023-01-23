package groups.intro

class DarkWilderness {

    fun growingPlant(upSpeed: Int, downSpeed: Int, desiredHeight: Int): Int {
        var currentHeight = 0
        var daysPassed = 0

        while (true) {
            daysPassed += 1
            currentHeight += upSpeed
            if (currentHeight >= desiredHeight) break;

            currentHeight -= downSpeed
        }
        return daysPassed
    }
}