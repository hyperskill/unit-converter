import java.util.*

class Converter {
    private val distancesNormalizer: Map<String, String> = mapOf(
            "m" to "meter",
            "meters" to "meter",
            "kilometers" to "kilometer",
            "km" to "kilometer",
            "cm" to "centimeter",
            "centimeters" to "centimeter",
            "mm" to "millimeter",
            "millimeters" to "millimeter",
            "mi" to "mile",
            "miles" to "mile",
            "yd" to "yard",
            "yards" to "yard",
            "ft" to "foot",
            "feet" to "foot",
            "in" to "inch",
            "inches" to "inch"
    )
    private val distancesInMeters: Map<String, Double> = mapOf(
            "meter" to 1.0,
            "kilometer" to 1000.0,
            "centimeter" to 0.01,
            "millimeter" to 0.001,
            "mile" to 1609.35,
            "yard" to 0.9144,
            "foot" to 0.3048,
            "inch" to 0.0254
    )
    private val pluralDistances: Map<String, String> = mapOf(
            "meter" to "meters",
            "kilometer" to "kilometers",
            "centimeter" to "centimeters",
            "millimeter" to "millimeters",
            "mile" to "miles",
            "yard" to "yards",
            "foot" to "feet",
            "inch" to "inches"
    )
    private val availableDistances: Set<String> = setOf(
            "meter",
            "kilometer",
            "centimeter",
            "millimeter",
            "mile",
            "yard",
            "foot",
            "inch"
    )

    private val weightsNormalizer: Map<String, String> = mapOf(
            "g" to "gram",
            "grams" to "gram",
            "kilograms" to "kilogram",
            "kg" to "kilogram",
            "mg" to "milligram",
            "milligrams" to "milligram",
            "lb" to "pound",
            "pounds" to "pound",
            "oz" to "ounce",
            "ounces" to "ounce"
    )
    private val weightsInGrams: Map<String, Double> = mapOf(
            "gram" to 1.0,
            "kilogram" to 1000.0,
            "milligram" to 0.001,
            "pound" to 453.592,
            "ounce" to 28.3495
    )
    private val pluralWeights: Map<String, String> = mapOf(
            "gram" to "grams",
            "kilogram" to "kilograms",
            "milligram" to "miligrams",
            "pound" to "pounds",
            "ounce" to "ounces"
    )
    private val availableWeights: Set<String> = setOf(
            "gram",
            "kilogram",
            "milligram",
            "pound",
            "ounce"
    )

    private val degreesNormalizer: Map<String, String> = mapOf(
            "c" to "celsius",
            "celsiuses" to "celsius",
            "dc" to "celsius",
            "f" to "fahrenheit",
            "df" to "fahrenheit",
            "fahrenheits" to "fahrenheit",
            "kelvins" to "kelvin",
            "k" to "kelvin",
            "dk" to "kelvin"
    )
    private val pluralDegrees: Map<String, String> = mapOf(
            "celsius" to "celsiuses",
            "fahrenheit" to "fahrenheits",
            "kelvin" to "kelvins"
    )
    private val availableDegrees: Set<String> = setOf(
            "celsius",
            "fahrenheit",
            "kelvin"
    )


    fun convertValues(from: String, to: String, value: Double) {
        var fromMeasure = normalizeMeasure(from)
        var toMeasure = normalizeMeasure(to)

        if (fromMeasure in availableDistances) {
            if (toMeasure !in availableDistances) {
                print("Conversion is impossible, try another one.\n")
                return
            }

            val newValue = convertDistances(fromMeasure, toMeasure, value)
            if (value != 1.0)
                fromMeasure = pluralDistances[fromMeasure]!!
            if (newValue != 1.0)
                toMeasure = pluralDistances[toMeasure]!!
            printConversion(value, fromMeasure, newValue, toMeasure)
        } else if (fromMeasure in availableWeights) {
            if (toMeasure !in availableWeights) {
                print("Conversion is impossible, try another one.\n")
                return
            }

            val newValue = convertWeights(fromMeasure, toMeasure, value)
            if (value != 1.0)
                fromMeasure = pluralWeights[fromMeasure]!!
            if (newValue != 1.0)
                toMeasure = pluralWeights[toMeasure]!!
            printConversion(value, fromMeasure, newValue, toMeasure)
        } else if (fromMeasure in availableDegrees) {
            if (toMeasure !in availableDegrees) {
                print("Conversion is impossible, try another one.\n")
                return
            }

            val newValue = convertDegrees(fromMeasure, toMeasure, value)
            if (value != 1.0)
                fromMeasure = pluralDegrees[fromMeasure]!!
            if (newValue != 1.0)
                toMeasure = pluralDegrees[toMeasure]!!
            printConversion(value, fromMeasure, newValue, toMeasure)
        } else {
            print("Conversion is impossible, try another one.\n")
            return
        }
    }

    private fun convertDistances(from: String, to: String, value: Double): Double {
        val valueInMeters = distancesInMeters[from]!! * value
        val newValueInMeters = distancesInMeters[to]!!
        return valueInMeters / newValueInMeters
    }

    private fun convertWeights(from: String, to: String, value: Double): Double {
        val valueInGrams = weightsInGrams[from]!! * value
        val newValueInGrams = weightsInGrams[to]!!
        return valueInGrams / newValueInGrams
    }

    private fun convertDegrees(from: String, to: String, value: Double): Double {
        if (from == "celsius" && to == "fahrenheit")
            return value * 1.8 + 32
        else if (to == "celsius" && from == "fahrenheit")
            return (value - 32) * (5.0 / 9.0)
        else if (from == "celsius" && to == "kelvin")
            return value + 273.15
        else if (to == "celsius" && from == "kelvin")
            return value - 273.15
        else if (from == "kelvin" && to == "fahrenheit")
            return value * 1.8 - 459.67
        return (value + 459.67) * (5.0 / 9.0)
    }

    private fun normalizeMeasure(measure: String): String {
        var newMeasure = measure.toLowerCase()
        if (newMeasure in distancesNormalizer)
            newMeasure = distancesNormalizer[newMeasure]!!
        else if (newMeasure in weightsNormalizer)
            newMeasure = weightsNormalizer[newMeasure]!!
        else if (newMeasure in degreesNormalizer)
            newMeasure = degreesNormalizer[newMeasure]!!
        return newMeasure
    }

    private fun printConversion(value: Double, from: String, newValue: Double, to: String) {
        println("$value $from is $newValue $to")
    }
}

fun main(args: Array<String>) {
    while (true) {
        print("Enter what you want to convert (or 0 to Exit): ")
        val scanner = Scanner(System.`in`)

        val value = scanner.nextDouble()
        if (value == 0.0)
            break

        val fromMeasure = scanner.next()
        scanner.skip(" [a-zA-Z]+ ")
        val toMeasure = scanner.next()

        Converter().convertValues(fromMeasure, toMeasure, value)
    }
}
