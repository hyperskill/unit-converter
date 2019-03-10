package unitConverter

import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var n: Double
    var k: Double = 0.0
    var s: String
    var s1: String
    do {
        print("Enter what you want to convert (or Exit): ")
        s = scanner.nextLine().toLowerCase()
        if (s == "exit") break
        val arr = s.split(" ")
        n = arr[0].toDouble()
        s = arr[1].toLowerCase()
        s1 = arr[3].toLowerCase()
        when (s) {
            "m", "meter", "meters" -> {
                k = n
                s = if (n == 1.0) "meter" else "meters"
            }
            "km", "kilometer", "kilometers" -> {
                k = n * 1000
                s = if (n == 1.0) "kilometer" else "kilometers"
            }
            "cm", "centimeter", "centimeters" -> {
                k = n / 100
                s = if (n == 1.0) "centimeter" else "centimeters"
            }
            "mm", "millimeter", "millimeters" -> {
                k = n / 1000
                s = if (n == 1.0) "millimeter" else "millimeters"
            }
            "mi", "mile", "miles" -> {
                k = n * 1609.35
                s = if (n == 1.0) "mile" else "miles"
            }
            "yd", "yard", "yards" -> {
                k = n * 0.9144
                s = if (n == 1.0) "yard" else "yards"
            }
            "ft", "foot", "feet" -> {
                k = n * 0.3048
                s = if (n == 1.0) "foot" else "feet"
            }
            "in", "inch", "inches" -> {
                k = n * 0.0254
                s = if (n == 1.0) "inch" else "inches"
            }
            "g", "gram", "grams" -> {
                k = n
                s = if (n == 1.0) "gram" else "grams"
            }
            "kg", "kilogram", "kilograms" -> {
                k = n * 1000
                s = if (n == 1.0) "kilogram" else "kilograms"
            }
            "mg", "milligram", "milligrams" -> {
                k = n / 1000
                s = if (n == 1.0) "milligram" else "milligrams"
            }
            "lb", "pound", "pounds" -> {
                k = n * 453.592
                s = if (n == 1.0) "pound" else "pounds"
            }
            "oz", "ounce", "ounces" -> {
                k = n * 28.3495
                s = if (n == 1.0) "ounce" else "ounces"
            }
        }
        when (s1) {
            "m", "meter", "meters" -> {
                s1 = if (k == 1.0) "meter" else "meters"
            }
            "km", "kilometer", "kilometers" -> {
                k /= 1000
                s1 = if (k == 1.0) "kilometer" else "kilometers"
            }
            "cm", "centimeter", "centimeters" -> {
                k *= 100
                s1 = if (k == 1.0) "centimeter" else "centimeters"
            }
            "mm", "millimeter", "millimeters" -> {
                k *= 1000
                s1 = if (k == 1.0) "millimeter" else "millimeters"
            }
            "mi", "mile", "miles" -> {
                k /= 1609.35
                s1 = if (k == 1.0) "mile" else "miles"
            }
            "yd", "yard", "yards" -> {
                k /= 0.9144
                s1 = if (k == 1.0) "yard" else "yards"
            }
            "ft", "foot", "feet" -> {
                k /= 0.3048
                s1 = if (k == 1.0) "foot" else "feet"
            }
            "in", "inch", "inches" -> {
                k /= 0.0254
                s1 = if (k == 1.0) "inch" else "inches"
            }
            "g", "gram", "grams" -> {
                s1 = if (k == 1.0) "gram" else "grams"
            }
            "kg", "kilogram", "kilograms" -> {
                k /= 1000
                s1 = if (k == 1.0) "kilogram" else "kilograms"
            }
            "mg", "milligram", "milligrams" -> {
                k *= 1000
                s1 = if (k == 1.0) "milligram" else "milligrams"
            }
            "lb", "pound", "pounds" -> {
                k /= 453.592
                s1 = if (k == 1.0) "pound" else "pounds"
            }
            "oz", "ounce", "ounces" -> {
                k /= 28.3495
                s1 = if (k == 1.0) "ounce" else "ounces"
            }
        }
        println("$n $s is $k $s1")
    } while (true)
}
