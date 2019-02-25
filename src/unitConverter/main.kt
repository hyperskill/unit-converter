package unitConverter

import java.util.*

fun normalizeMeasure(measure:String): String = when(measure.toLowerCase()) {
    "m", "meter", "meters" -> "meters"
    "km", "kilometer", "kilometers" -> "kilometers"
    "cm", "centimeter", "centimeters" -> "centimeters"
    "mm", "millimeter", "millimeters" -> "millimeters"
    "mi", "mile", "miles" -> "miles"
    "yd", "yard", "yards" -> "yards"
    "ft", "foot", "feet" -> "feet"
    "in", "inch", "inches" -> "inches"
    "g", "gram", "grams" -> "grams"
    "kg", "kilogram", "kilograms" -> "kilograms"
    "mg", "milligram", "milligrams" -> "milligrams"
    "lb", "pound", "pounds" -> "pounds"
    "oz", "ounce", "ounces" -> "ounces"

    else -> error("can't normalize measure '$measure'")
}

fun lengthFactor(measure:String): Double = when(measure) {
    "meters" -> 1.0
    "kilometers" -> 1000.0
    "centimeters" -> 0.01
    "millimeters" -> 0.001
    "miles" -> 1609.35
    "yards" -> 0.9144
    "feet" -> 0.3048
    "inches" -> 0.0254

    else -> error("unknown length measure")
}

fun weightFactor(measure:String): Double = when(measure) {
    "grams" -> 1.0
    "kilograms" -> 1000.0
    "milligrams" -> 0.001
    "pounds" -> 453.592
    "ounces" -> 28.3495

    else -> error("unknown weight measure")
}

fun lengthConverter(fromMeasure:String, toMeasure:String, amount:Double = 1.0): Double {
    val factorFrom = lengthFactor(fromMeasure)
    val factorTo = lengthFactor(toMeasure)

    return amount * factorFrom / factorTo
}

fun weightConverter(fromMeasure:String, toMeasure:String, amount:Double = 1.0): Double {
    val factorFrom = weightFactor(fromMeasure)
    val factorTo = weightFactor(toMeasure)

    return amount * factorFrom / factorTo
}

fun isWeightMeasure(measure:String): Boolean = when(measure) {
    "grams",
    "kilograms",
    "milligrams",
    "pounds",
    "ounces" -> true

    else -> false
}

fun isLengthMeasure(measure:String): Boolean = when(measure) {
    "meters",
    "kilometers",
    "centimeters",
    "millimeters",
    "miles",
    "yards",
    "feet",
    "inches" -> true

    else -> false
}

fun calculate(fromMeasure:String, toMeasure:String, amount:Double): Double = when {
    isWeightMeasure(fromMeasure) -> weightConverter(fromMeasure, toMeasure, amount)
    isLengthMeasure(fromMeasure) -> lengthConverter(fromMeasure, toMeasure, amount)

    else -> error("Unknown measure type")
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    while (true) {
        print("Enter what you want to convert (or Exit): ")

        if (!scanner.hasNextDouble()) {
            val command = scanner.next().toLowerCase()

            if (command == "exit") {
                break
            }
        }

        val amount = scanner.nextDouble()
        val fromMeasure = normalizeMeasure(scanner.next())
        scanner.next()
        val toMeasure = normalizeMeasure(scanner.next())
        val result = calculate(fromMeasure, toMeasure, amount)

        println("$amount $fromMeasure is $result $toMeasure")
    }
}