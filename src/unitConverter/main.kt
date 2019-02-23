package unitConverter

import java.util.*

fun kilograms2grams(kilograms: Int): Long = 1000L * kilograms

fun main(args: Array<String>) {
    print("Enter a number of kilograms: ")

    val scanner = Scanner(System.`in`)
    val amount = scanner.nextInt()

    print("$amount kilograms is ${kilograms2grams(amount)} grams")
}