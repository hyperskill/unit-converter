package unitConverter

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    println("Enter a number of kilograms: ")
    val kg = scanner.nextInt()
    println("$kg kilograms is ${kg * 1000} grams")
}