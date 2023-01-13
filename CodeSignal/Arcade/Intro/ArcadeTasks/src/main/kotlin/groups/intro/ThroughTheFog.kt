package groups.intro

import java.lang.Math.abs
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
        val differense: Double = threshold.toDouble() / deposit.toDouble()
        return ceil(log(differense, (1 + percents))).toInt()
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
}