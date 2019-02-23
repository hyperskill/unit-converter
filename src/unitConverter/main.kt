package unitConverter

import java.util.*

fun kilometers2meters(kilometers: Double): Double = kilometers * 1000

fun centimeters2meters(centimeters: Double): Double = centimeters * 0.01

fun millimeters2meters(millimeters: Double): Double = millimeters * 0.001

fun miles2meters(miles: Double): Double = miles * 1609.35

fun yard2meters(yard: Double): Double = yard * 0.9144

fun feet2meters(foot: Double): Double = foot * 0.3048

fun inch2meters(inch: Double): Double = inch * 0.0254

fun formatMeasure(measure:String): String {
    val measureLower = measure.toLowerCase()

    return when {
        arrayOf("m", "meter", "meters").contains(measureLower) -> "meter"
        arrayOf("km", "kilometer", "kilometers").contains(measureLower) -> "kilometers"
        arrayOf("cm", "centimeter", "centimeters").contains(measureLower) -> "centimeters"
        arrayOf("mm", "millimeter", "millimeters").contains(measureLower) -> "millimeters"
        arrayOf("mi", "mile", "miles").contains(measureLower) -> "mile"
        arrayOf("yd", "yard", "yards").contains(measureLower) -> "yards"
        arrayOf("ft", "foot", "feet").contains(measureLower) -> "feet"
        arrayOf("in", "inch", "inches").contains(measureLower) -> "inches"
        else -> ""
    }
}

fun convert2meter(measure: String, amount: Double): Double = when (measure) {
    "meter" -> amount
    "kilometers" -> kilometers2meters(amount)
    "centimeters" -> centimeters2meters(amount)
    "millimeters" -> millimeters2meters(amount)
    "mile" -> miles2meters(amount)
    "yards" -> yard2meters(amount)
    "feet" -> feet2meters(amount)
    "inches" -> inch2meters(amount)
    else -> 0.0
}

fun main(args: Array<String>) {
    print("Enter a number and a measure of length: ")

    val scanner = Scanner(System.`in`)
    val amount = scanner.nextDouble()
    val measure = formatMeasure(scanner.next())
    val inMeters = convert2meter(measure, amount)

    print("$amount $measure is $inMeters meters")
}