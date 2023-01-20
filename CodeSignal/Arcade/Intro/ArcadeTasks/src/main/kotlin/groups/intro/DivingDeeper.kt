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

    fun firstDigit(inputString: String): Char {
        return inputString.first { it.isDigit() }
    }

    fun differentSymbolsNaive(s: String): Int {
        val symbols = mutableMapOf<Char, Int>()
        s.forEach {
            if (!symbols.containsKey(it)) {
                symbols[it] = 1
            }
        }
        return symbols.size
    }

    fun arrayMaxConsecutiveSum(inputArray: MutableList<Int>, k: Int): Int {
        var max = Int.MIN_VALUE

        for (i in 0..inputArray.size - k) {
            val sum = inputArray.subList(i, i + k).sum()
            if (sum > max) {
                max = sum
            }
        }
        return max
    }
}