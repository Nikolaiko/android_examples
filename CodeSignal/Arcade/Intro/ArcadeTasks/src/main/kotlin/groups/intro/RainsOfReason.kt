package groups.intro

class RainsOfReason {
    fun arrayReplace(inputArray: MutableList<Int>, elemToReplace: Int, substitutionElem: Int): MutableList<Int> {
        inputArray.replaceAll {
            when(it == elemToReplace) {
                true -> substitutionElem
                false -> it
            }
        }
        return inputArray
    }

    fun evenDigitsOnly(n: Int): Boolean {
        val stringDigits = n.toString()
        return stringDigits.none { it.code % 2 != 0 }
    }

    fun variableName(name: String): Boolean {
        return try {
            val result = name.filter { !it.isLetterOrDigit() && it!= '_' }
            !name.first().isDigit() && result.isEmpty()
        } catch (exception: NoSuchElementException) {
            false
        }
    }

    fun alphabeticShift(inputString: String): String {
        val aCode = 97
        val zCode = 122
        val builder = StringBuilder()
        for (character: Char in inputString) {
            val newCharCode = when(character.code == zCode) {
                true -> aCode
                false -> character.code + 1
            }
            val newChar = (newCharCode).toChar()

            builder.append(newChar)
        }
        return builder.toString()
    }

    fun chessBoardCellColor(cell1: String, cell2: String): Boolean {
        val blackCode = 55
        val whiteCode = 67

        val firstRowIndex = cell1.last().digitToInt()
        val firstColumnIndex = (cell1.first().code - 'A'.code) + 1

        val secondRowIndex = cell2.last().digitToInt()
        val secondColumnIndex = (cell2.first().code - 'A'.code) + 1


        val firstColor = when (firstRowIndex % 2 == 0) {
            true -> {
                when(firstColumnIndex % 2 == 0) {
                    true -> blackCode
                    false -> whiteCode
                }
            }
            false -> {
                when(firstColumnIndex % 2 == 0) {
                    true -> whiteCode
                    false -> blackCode
                }
            }
        }

        val secondColor = when (secondRowIndex % 2 == 0) {
            true -> {
                when(secondColumnIndex % 2 == 0) {
                    true -> blackCode
                    false -> whiteCode
                }
            }
            false -> {
                when(secondColumnIndex % 2 == 0) {
                    true -> whiteCode
                    false -> blackCode
                }
            }
        }

        return firstColor == secondColor
    }


}