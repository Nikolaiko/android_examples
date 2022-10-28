package groups

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
        return false
    }
}