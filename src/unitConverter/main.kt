package unitConverter

import java.util.*

fun main(args: Array<String>) {
    val factorMap = mapOf<String, Double>(
        "m" to 1.0, "km" to 1000.0, "cm" to 0.01, "mm" to 0.001,
        "mi" to 1609.35, "yd"  to 0.9144, "ft" to  0.3048, "in" to 0.0254)
    val unitNamesMap = mapOf<String, Pair<String, String>>(
        "m" to Pair("meter", "meters"), "km" to Pair("kilometer", "kilometers"),
        "cm" to Pair("centimeter", "centimeters"), "mm" to Pair("millimeter", "millimeters"),
        "mi" to Pair("mile", "miles"), "yd"  to Pair("yard", "yards"),
        "ft" to Pair("foot", "feet"), "in" to Pair("inch", "inches"))

    val scanner = Scanner(System.`in`)
    println("Enter a number and a measure of length:")
    val value = scanner.next().toDouble()
    val initialUnit = scanner.next().toLowerCase()
    val unitAcronym = when(initialUnit) {
        "m", "meter", "meters" -> "m"
        "km", "kilometer", "kilometers" -> "km"
        "cm", "centimeter", "centimeters" -> "cm"
        "mm", "millimeter", "millimeters" -> "mm"
        "mi", "mile", "miles" -> "mi"
        "yd", "yard", "yards" -> "yd"
        "ft", "foot", "feet" -> "ft"
        "in", "inch", "inches" -> "in"
        else -> ""
    }
    val correctInitialUnit = if (value == 1.0) unitNamesMap[unitAcronym]?.first
                                    else unitNamesMap[unitAcronym]?.second
    val result = factorMap.getValue(unitAcronym) * value
    val correctMetersUnit = if (result == 1.0) "meter" else "meters"

    println("$value $correctInitialUnit is $result $correctMetersUnit")
}