package groups

class ExploringTheWaters {
    fun alternatingSums(a: MutableList<Int>): MutableList<Int> {
        val sorted = mutableListOf(0, 0)
        for (index in a.indices) {
            when ((index + 1) % 2 == 0) {
                true -> sorted[1] += a[index]
                false -> sorted[0] += a[index]
            }
        }
        return sorted
    }

    fun addBorder(picture: MutableList<String>): MutableList<String> {
        val result = mutableListOf<String>()

        result.add("*".repeat(picture.first().length + 2))
        picture.forEach { result.add("*$it*") }
        result.add("*".repeat(picture.first().length + 2))
        return result
    }

    fun areSimilars(a: MutableList<Int>, b: MutableList<Int>): Boolean {
        var differences = 0
        val firstOut = mutableListOf<Int>()
        val secondOut = mutableListOf<Int>()
        for (index in a.indices) {
            if (a[index] != b[index]) {
                differences += 1
                firstOut.add(a[index])
                secondOut.add(b[index])
                if (differences > 2) {
                    return false
                }
            }
        }
        return firstOut.containsAll(secondOut)
    }

    fun arrayChange(inputArray: MutableList<Int>): Int {
        var totalDifference = 0
        for (index in 0..inputArray.size - 2) {
            if (inputArray[index] >= inputArray[index + 1]) {
                val localDifference = inputArray[index] - inputArray[index + 1] + 1
                inputArray[index + 1] += localDifference

                totalDifference += localDifference
            }
        }
        return totalDifference
    }

    fun palindromeRearranging(input: String): Boolean {
        val isLengthEven = input.length % 2 == 0
        val lettersMap = mutableMapOf<Char, Int>()

        input.forEach { lettersMap[it] = (lettersMap[it] ?: 0) + 1 }

        var oddLettersCount = 0
        lettersMap.forEach {
            if (it.value % 2 != 0) {
                oddLettersCount += 1
            }

            when(isLengthEven) {
                true -> { if (oddLettersCount > 0) return false }
                false -> { if (oddLettersCount > 1) return false }
            }
        }
        return true
    }

}