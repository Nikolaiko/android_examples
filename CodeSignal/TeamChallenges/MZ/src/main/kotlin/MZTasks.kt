class MZTasks {
    /*
        You and your alliance of warriors are trying to kill a monster to score points in a Kingdom vs. Kingdom (KvK) event. Each unit (both a warrior and a monster are considered a unit) has a certain number of health points (healthPoints) and attack damage value (attackDamage). When one unit attacks another, the health of the unit that is under attack is decreased by the attacker's damage value. If a unit's health points are reduced to zero or less, the unit dies and can't take part in the battle anymore.

        The skirmish between the warrior alliance and the monster proceeds in the following way:

        Each turn, you direct one of your warriors to attack the monster.
        If the monster dies, you win.
        If the monster is still alive after an attack, it counter-attacks the same warrior who attacked it in the previous step.
        If all of your warriors die, you lose.
        Find the maximum number of your warriors that will remain after defeating the monster. If it's impossible to kill a monster without losing all your warriors, return 0.
     */
    fun allianceVersusMonster(healthPoints: MutableList<Int>, attackDamage: MutableList<Int>): Int {
        val monster = Character(
            health = healthPoints.removeFirst(),
            damage = attackDamage.removeFirst()
        )

        val soldiers = mutableListOf<Character>()
        for (index in 0..healthPoints.lastIndex) {
            soldiers.add(
                Character(
                    health = healthPoints[index],
                    damage = attackDamage[index]
                )
            )
        }

        soldiers.sortByDescending {
            it.health
        }

        for (soldier in soldiers) {
            when (soldier.health > monster.damage) {
                true -> {
                    val hits = when(soldier.health % monster.damage) {
                        0 -> (soldier.health / monster.damage) - 1
                        else -> soldier.health / monster.damage
                    }
                    monster.health -= soldier.damage * hits
                    soldier.health -= monster.damage * hits
                    if (monster.health <= 0) {
                        return soldiers.size
                    }
                }
                false -> break
            }
        }

        soldiers.sortByDescending {
            it.damage
        }

        for (soldier in soldiers) {
            monster.health -= soldier.damage
            when (monster.health <= 0) {
                true -> return  soldiers.filter { it.health > 0 }.size
                false -> soldier.health -= monster.damage
            }
        }
        return 0
    }


}