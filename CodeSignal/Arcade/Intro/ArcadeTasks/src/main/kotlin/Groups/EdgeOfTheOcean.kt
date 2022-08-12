class EdgeOfTheOcean {
    fun shapeArea(n: Int): Int {
        val result = when (n > 1) {
            true -> shapeArea(n - 1) + 4 + (n - 2) * 4
            false -> 1
        }
        return result
    }
}