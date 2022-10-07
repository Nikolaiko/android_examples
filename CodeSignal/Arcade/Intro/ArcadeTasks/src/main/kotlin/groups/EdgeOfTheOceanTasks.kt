package groups

class EdgeOfTheOceanTasks {
    fun shapeArea(n: Int): Int {
        val result = when (n > 1) {
            true -> shapeArea(n - 1) + 4 + (n - 2) * 4
            false -> 1
        }
        return result
    }

    fun makeArrayConsecutive2(statues: List<Int>): Int {
        return (statues.maxOrNull() ?: 0) - (statues.minOrNull() ?: 0) - (statues.size - 1)
    }

    fun almostIncreasingSequence(sequence: MutableList<Int>): Boolean {
        val clone = sequence.toList()
        var lastNumber = -100

        for (index in clone.indices) {
            when(index == 0) {
                true -> lastNumber = clone[index]
                false -> {
                    when(lastNumber >= clone[index]) {
                        false -> lastNumber = clone[index]
                        true -> {
                            var tryArr = sequence.toMutableList()
                            tryArr.removeAt(index)

                            println("After  : $tryArr")
                            println("After  : ${ifListSorted(tryArr)}")

                            if (!ifListSorted(tryArr)) {
                                if (index != 0) {
                                    tryArr = sequence.toMutableList()
                                    tryArr.removeAt(index - 1)
                                    println("After first : $tryArr")
                                    println("After first : ${ifListSorted(tryArr)}")
                                    return ifListSorted(tryArr)
                                } else {
                                    return false
                                }
                            } else {
                                return true
                            }
                        }
                    }
                }
            }
        }
        return true
    }

    private fun ifListSorted(list: List<Int>): Boolean {
        var lastNumber = -100
        for(index in list.indices) {
            when(index == 0) {
                true -> lastNumber = list[index]
                false -> {
                    if (lastNumber >= list[index]) {
                       return false
                    }
                    lastNumber = list[index]
                }
            }
        }
        return true
    }

    fun matrixElementsSum(matrix: MutableList<MutableList<Int>>): Int {
        val height = matrix.size
        val width = matrix.first().size
        var summ = 0

        for (column in 0 until width) {
            for (row in 0 until height) {
                when (matrix[row][column] > 0) {
                    true -> summ += matrix[row][column]
                    false -> break
                }
            }
        }
        return summ
    }
}