package groups.intro

class DivingDeeper {
    fun extractEachKth(inputArray: MutableList<Int>, k: Int): MutableList<Int> {
        var currentIndex = k - 1
        val indexes = mutableListOf<Int>()

        while(currentIndex <= inputArray.size - 1) {
            indexes.add(currentIndex)
            currentIndex += k
        }

        var removedCount = 0
        for (index in indexes.indices) {
            inputArray.removeAt(indexes[index] - removedCount)
            removedCount += 1
        }
        return inputArray
    }
}