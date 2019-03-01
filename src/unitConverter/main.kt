package unitConverter

import java.util.*

enum class Length (val factor: Double, val singular:String, val plural:String) {
    MILLIMETER(0.001, "millimeter", "millimeters"),
    CENTIMETER(0.01, "centimeter", "centimeters"),
    INCH(0.0254, "inch", "inches"),
    FOOT(0.3048, "foot", "feet"),
    YARD(0.9144, "yard", "yards"),
    METER(1.0, "meter", "meters"),
    KILOMETER(1000.0, "kilometer", "kilometers"),
    MILE(1609.35, "mile", "miles")
}

enum class Weight(val factor: Double, val singular:String, val plural:String) {
    GRAM(1.0, "gram", "grams"),
    KILOGRAM(1000.0, "kilogram", "kilograms"),
    MILLIGRAM(0.001, "milligram", "milligrams"),
    POUND(453.592, "pound", "pounds"),
    OUNCE(28.3495, "ounce", "ounces")
}

enum class Temperature(val singular:String, val plural:String) {
    CELSIUS("degree Celsius", "degrees Celsius"),
    FAHRENHEIT("degree Fahrenheit", "degrees Fahrenheit"),
    KELVIN("Kelvin", "Kelvins")
}

fun normalizeMeasure(measure: String): String = when (measure.toLowerCase()) {
    "m", "meter", "meters" -> Length.METER.name
    "km", "kilometer", "kilometers" -> Length.KILOMETER.name
    "cm", "centimeter", "centimeters" -> Length.CENTIMETER.name
    "mm", "millimeter", "millimeters" -> Length.MILLIMETER.name
    "mi", "mile", "miles" -> Length.MILE.name
    "yd", "yard", "yards" -> Length.YARD.name
    "ft", "foot", "feet" -> Length.FOOT.name
    "in", "inch", "inches" -> Length.INCH.name
    "g", "gram", "grams" -> Weight.GRAM.name
    "kg", "kilogram", "kilograms" -> Weight.KILOGRAM.name
    "mg", "milligram", "milligrams" -> Weight.MILLIGRAM.name
    "lb", "pound", "pounds" -> Weight.POUND.name
    "oz", "ounce", "ounces" -> Weight.OUNCE.name
    "degree celsius", "degrees celsius", "dc", "c" -> Temperature.CELSIUS.name
    "degree fahrenheit", "degrees fahrenheit", "df", "f" -> Temperature.FAHRENHEIT.name
    "kelvin", "kelvins", "k" -> Temperature.KELVIN.name

    else -> "???"
}

fun lengthConverter(fromMeasure: Length, toMeasure: Length, amount: Double = 1.0): Double {
    return amount * fromMeasure.factor / toMeasure.factor
}

fun weightConverter(fromMeasure: Weight, toMeasure: Weight, amount: Double = 1.0): Double {
    return amount * fromMeasure.factor / toMeasure.factor
}

fun temperatureConverter(fromMeasure: Temperature, toMeasure: Temperature, amount: Double = 1.0): Double {
    return when {
        fromMeasure == Temperature.CELSIUS && toMeasure == Temperature.KELVIN -> amount + 273.15
        fromMeasure == Temperature.CELSIUS && toMeasure == Temperature.FAHRENHEIT -> amount * 9 / 5 + 32
        fromMeasure == Temperature.KELVIN && toMeasure == Temperature.CELSIUS -> amount - 273.15
        fromMeasure == Temperature.KELVIN && toMeasure == Temperature.FAHRENHEIT -> amount * 9 / 5 - 459.67
        fromMeasure == Temperature.FAHRENHEIT && toMeasure == Temperature.CELSIUS -> (amount - 32) * 5 / 9
        fromMeasure == Temperature.FAHRENHEIT && toMeasure == Temperature.KELVIN -> (amount + 459.67) * 5 / 9

        else -> amount
    }
}

fun isWeightMeasure(normalizedMeasure: String): Boolean {
    return Weight.values().any { it.name == normalizedMeasure }
}

fun isLengthMeasure(normalizedMeasure: String): Boolean {
    return Length.values().any { it.name == normalizedMeasure }
}

fun isTemperatureMeasure(normalizedMeasure: String): Boolean {
    return Temperature.values().any { it.name == normalizedMeasure }
}

fun getLabel(normalizedMeasure: String, amount:Double = 1.0):String = when {
    isWeightMeasure(normalizedMeasure) -> getLabel(Weight.valueOf(normalizedMeasure), amount)
    isLengthMeasure(normalizedMeasure) -> getLabel(Length.valueOf(normalizedMeasure), amount)
    isTemperatureMeasure(normalizedMeasure) -> getLabel(Temperature.valueOf(normalizedMeasure), amount)

    else -> "???"
}

fun getLabel(measure: Weight, amount: Double): String {
    if (amount == 1.0) {
        return measure.singular
    }

    return measure.plural
}

fun getLabel(measure: Length, amount: Double): String {
    if (amount == 1.0) {
        return measure.singular
    }

    return measure.plural
}

fun getLabel(measure: Temperature, amount: Double): String {
    if (amount == 1.0) {
        return measure.singular
    }

    return measure.plural
}

fun calculate(fromMeasure: String, toMeasure: String, amount: Double) {
    val normalizedFrom = normalizeMeasure(fromMeasure)
    val normalizedTo = normalizeMeasure(toMeasure)
    val conversion:Double

    when {
        isWeightMeasure(normalizedFrom) && isWeightMeasure(normalizedTo) -> {
            conversion = weightConverter(Weight.valueOf(normalizedFrom), Weight.valueOf(normalizedTo), amount)
        }

        isLengthMeasure(normalizedFrom) && isLengthMeasure(normalizedTo) -> {
            conversion = lengthConverter(Length.valueOf(normalizedFrom), Length.valueOf(normalizedTo), amount)
        }

        isTemperatureMeasure(normalizedFrom) && isTemperatureMeasure(normalizedTo) -> {
            conversion = temperatureConverter(Temperature.valueOf(normalizedFrom), Temperature.valueOf(normalizedTo), amount)
        }

        else -> {
            val fromLabel = getLabel(normalizedFrom);
            val toLabel = when(fromLabel == "???") {
                true -> getLabel(normalizedTo, 2.0)
                false -> getLabel(normalizedTo)
            }

            println("Conversion from ${fromLabel} to ${toLabel} is impossible")

            return
        }
    }

    println("$amount ${getLabel(normalizedFrom, amount)} is $conversion ${getLabel(normalizedTo, conversion)}")
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
        var fromMeasure = scanner.next()

        while (scanner.hasNext()) {
            val part = scanner.next()

            if (part.toLowerCase() in arrayOf("in", "to")) {
                break;
            }

            fromMeasure += " $part"
        }

        val toMeasure = scanner.nextLine().trim()

        calculate(fromMeasure, toMeasure, amount)
    }
}