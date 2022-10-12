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

}