package unitConverter

import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var n: Int
    do {
        print("Enter a number of kilograms: ")
        n = scanner.nextInt()
    } while (n < 0)
    println("$n kilograms is ${n * 1000} grams")
}
