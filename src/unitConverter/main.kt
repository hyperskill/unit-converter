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

fun getLabel(amount: Double, singular:String, plural: String) = when(amount == 1.0) {
    true -> singular
    false -> plural
}

fun calculate(fromMeasure: String, toMeasure: String, amount: Double) {
    val normalizedFrom = normalizeMeasure(fromMeasure)
    val normalizedTo = normalizeMeasure(toMeasure)
    var conversion = 0.0
    var amountLabel = ""
    var conversionLabel = ""

    when {
        isWeightMeasure(normalizedFrom) && isWeightMeasure(normalizedTo) -> {
            val fromMeasureEnum = Weight.valueOf(normalizedFrom)
            val toMeasureEnum = Weight.valueOf(normalizedTo)

            conversion = weightConverter(fromMeasureEnum, toMeasureEnum, amount)
            amountLabel = getLabel(amount, fromMeasureEnum.singular, fromMeasureEnum.plural)
            conversionLabel = getLabel(conversion, toMeasureEnum.singular, toMeasureEnum.plural)
        }

        isLengthMeasure(normalizedFrom) && isLengthMeasure(normalizedTo) -> {
            val fromMeasureEnum = Length.valueOf(normalizedFrom)
            val toMeasureEnum = Length.valueOf(normalizedTo)

            conversion = lengthConverter(fromMeasureEnum, toMeasureEnum, amount)
            amountLabel = getLabel(amount, fromMeasureEnum.singular, fromMeasureEnum.plural)
            conversionLabel = getLabel(conversion, toMeasureEnum.singular, toMeasureEnum.plural)
        }

        isTemperatureMeasure(normalizedFrom) && isTemperatureMeasure(normalizedTo) -> {
            val fromMeasureEnum = Temperature.valueOf(normalizedFrom)
            val toMeasureEnum = Temperature.valueOf(normalizedTo)

            conversion = temperatureConverter(fromMeasureEnum, toMeasureEnum, amount)
            amountLabel = getLabel(amount, fromMeasureEnum.singular, fromMeasureEnum.plural)
            conversionLabel = getLabel(conversion, toMeasureEnum.singular, toMeasureEnum.plural)
        }

        else -> {
            println("Conversion from ${normalizedFrom.toLowerCase()} to ${normalizedTo.toLowerCase()} is impossible")

            return
        }
    }

    println("$amount $amountLabel is $conversion $conversionLabel")
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