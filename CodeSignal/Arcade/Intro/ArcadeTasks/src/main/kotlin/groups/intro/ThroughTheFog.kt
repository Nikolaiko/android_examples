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
//        var currentDeposit = deposit
//        var yearsCount = 0
//        do {
//            currentDeposit += (currentDeposit * rate) / 100
//            yearsCount += 1
//        } while (currentDeposit < threshold)
        val percents: Double = rate.toDouble() / 100
        val differense: Double = threshold.toDouble() / deposit.toDouble()
        return ceil(log(differense, (1 + percents))).toInt()
    }
}