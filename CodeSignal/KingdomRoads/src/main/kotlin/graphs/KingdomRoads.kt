package graphs

class KingdomRoads {
    fun newRoadSystem(roadRegister: MutableList<MutableList<Boolean>>): Boolean {
        for (i in roadRegister.indices) {
            val outputWays = roadRegister[i].filter { it }.size
            val inputWays = roadRegister.filter { it[i] }.size
            if (outputWays != inputWays) return false
        }
        return true
    }
}