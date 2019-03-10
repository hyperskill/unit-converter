package unitConverter

import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var n: Double
    var k: Double = 0.0
    var s: String
    var s1: String = "meters"
    do {
        print("Enter a number and a measure of length: ")
        n = scanner.nextDouble()
        s = scanner.next().toLowerCase()
    } while (n < 0)
    when (s) {
        "m", "meter", "meters" -> {
            k = n
            s = if (n== 1.0) "meter" else "meters"
        }
        "km", "kilometer", "kilometers" -> {
            k = n * 1000
            s = if (n== 1.0) "kilometer" else "kilometers"
        }
        "cm", "centimeter", "centimeters" -> {
            k = n / 100
            s = if (n== 1.0) "centimeter" else "centimeters"
        }
        "mm", "millimeter", "millimeters" -> {
            k = n / 1000
            s = if (n== 1.0) "millimeter" else "millimeters"
        }
        "mi", "mile", "miles" -> {
            k = n * 1609.35
            s = if (n== 1.0) "mile" else "miles"
        }
        "yd", "yard", "yards" -> {
            k = n * 0.9144
            s = if (n== 1.0) "yard" else "yards"
        }
        "ft", "foot", "feet" -> {
            k = n * 0.3048
            s = if (n== 1.0) "foot" else "feet"
        }
        "in", "inch", "inches" -> {
            k = n * 0.0254
            s = if (n== 1.0) "inch" else "inches"
        }
    }
    if (k==1.0) s1 = "meter"
    println("$n $s is $k $s1")
}
