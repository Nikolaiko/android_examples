package groups.intro

class SmoothSailing {
    fun allLongestStrings(inputArray: MutableList<String>): MutableList<String> {
        var maxLength = 0
        for (curString in inputArray) {
            if (curString.length > maxLength) {
                maxLength = curString.length
            }
        }

        return inputArray.filter { it.length == maxLength }.toMutableList()
    }

    fun commonCharacterCount(s1: String, s2: String): Int {
        var (maxString, anotherString) = when(s1.length > s2.length) {
            true -> Pair(s1, s2)
            false -> Pair(s2, s1)
        }

        var commonCharsCount = 0
        maxString.forEach {
            if (anotherString.contains(it)) {
                commonCharsCount += 1
                val index = anotherString.indexOf(it)
                anotherString = anotherString.removeRange(index, index + 1)
            }
        }
        return commonCharsCount
    }

    fun isLucky(n: Int): Boolean {
        val numberString = n.toString()
        val firstPart = numberString.removeRange(0, numberString.length / 2)
        val secondPart = numberString.removeRange(numberString.length / 2, numberString.length)

        var leftPart = 0
        firstPart.forEach {
            leftPart += it.code
        }

        var rightPart = 0
        secondPart.forEach {
            rightPart += it.code
        }
        return rightPart == leftPart
    }

    fun sortByHeight(a: MutableList<Int>): MutableList<Int> {
        val treeValue = -1
        val peoples = a.filter { it != treeValue }.sorted().toMutableList()
        val result = mutableListOf<Int>()
        a.forEach {
            when (it == treeValue) {
                true -> result.add(it)
                false -> result.add(peoples.removeFirst())
            }
        }
        return result
    }

    fun reverseInParentheses(inputString: String): String {
        var stringCopy = inputString
        val openBraces = mutableListOf<Int>()
        var index = 0

        inputString.forEach {
            if (it == '(') {
                openBraces.add(index)
            } else if (it == ')') {
                val openBraceIndex = openBraces.removeLast()
                val closeBraceIndex = index
                var bracesString = stringCopy.substring(openBraceIndex + 1, closeBraceIndex)

                bracesString = bracesString.reversed()
                stringCopy = stringCopy.replaceRange(openBraceIndex + 1, closeBraceIndex, bracesString)
            }
            index += 1
        }

        stringCopy = stringCopy.replace(")", "")
        stringCopy = stringCopy.replace("(", "")

        return stringCopy
    }
}