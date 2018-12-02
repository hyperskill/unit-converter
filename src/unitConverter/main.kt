package unitConverter
import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    print("Enter a number of kilograms: ")
    val kilograms = scanner.nextInt()
    println("$kilograms kilograms is ${kilograms * 1000} grams")
}