package groups.intro

import kotlin.math.ceil
import kotlin.math.log

class ThroughTheFog {
    fun circleOfNumbers(n: Int, firstNumber: Int): Int {
        val halfValue = (n / 2) - 1
        val maxValue = n - 1

        return when {
            firstNumber == halfValue -> maxValue
            firstNumber == maxValue -> halfValue
            firstNumber < halfValue -> firstNumber + (n / 2)
            else -> firstNumber - (n / 2)
        }
    }

    fun depositProfit(deposit: Int, rate: Int, threshold: Int): Int {
        val percents: Double = rate.toDouble() / 100
        val difference: Double = threshold.toDouble() / deposit.toDouble()
        return ceil(log(difference, (1 + percents))).toInt()
    }

    fun absoluteValuesSumMinimization(a: MutableList<Int>): Int {
        var minAbsSum = Int.MAX_VALUE
        var result = Int.MAX_VALUE

        a.forEach { currentValue: Int ->
            val middle = a.fold(0) { acc, i ->
                acc + kotlin.math.abs(i - currentValue)
            }
            when {
                middle < minAbsSum ||
                middle == minAbsSum && currentValue < result -> {
                    minAbsSum = middle
                    result = currentValue
                }

            }
        }

        return result
    }

    fun stringsRearrangement(inputArray: MutableList<String>): Boolean {
        val starting = mutableListOf<String>()

        inputArray.forEach { currentString: String ->
            val haveNear = inputArray.any { differenceCount(currentString, it) == 1 }
            if (haveNear) {
                starting.add(currentString)
            }
        }

        if (starting.isEmpty()) return false
        starting.forEach {
            val othersCopy = inputArray.toMutableList().apply { remove(it) }
            if (checkStepForDifference(it, othersCopy)) return true
        }
        return false
    }

    private fun checkStepForDifference(currentStep: String, others: MutableList<String>): Boolean {
        if (others.isEmpty()) return true

        val nextStep = others.filter { differenceCount(currentStep, it) == 1 }
        if (nextStep.isEmpty()) return false

        nextStep.forEach {
            val othersCopy = others.toMutableList().apply { remove(it) }
            if (checkStepForDifference(it, othersCopy)) return true
        }
        return false
    }

    private fun differenceCount(s1: String, s2: String): Int {
        if (s1.length != s2.length) return Int.MAX_VALUE
        var difference = 0
        for(i in s1.indices) {
            if (s1[i] != s2[i]) difference += 1
        }
        return difference
    }

}