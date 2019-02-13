package unitConverter

import java.util.*

fun main(args: Array<String>) {
    val unitsMap = initUnitsMap()
    while (true) {
        val (value, initialUnit, finalUnit, exitFlag) = readData(unitsMap)
        if (exitFlag) {
            break
        }
        if (isPossibleToConvert(initialUnit, finalUnit) == true && initialUnit != null && finalUnit != null) {
            val result: Double = if (initialUnit.type == UnitTypes.TEMPERATURE) {
                convertTemperature(value, initialUnit, finalUnit)
            } else {
                value * initialUnit.factor / finalUnit.factor
            }
            val correctInitialUnit = if (value == 1.0) initialUnit.single else initialUnit.multiple
            val correctFinalUnit = if (result == 1.0) finalUnit.single else finalUnit.multiple
            println("$value $correctInitialUnit is $result $correctFinalUnit")
        } else {
            var correctInitialUnit = "???"
            var correctFinalUnit = "???"
            if (initialUnit != null) {
                correctInitialUnit = initialUnit.multiple
            }
            if (finalUnit != null) {
                correctFinalUnit = finalUnit.multiple
            }
            println("Conversion from $correctInitialUnit to $correctFinalUnit is impossible")
        }
    }
}

fun convertTemperature(value: Double, initialUnit: Unit, finalUnit: Unit): Double {
    return when (initialUnit.acronym) {
        "c" -> {
            when (finalUnit.acronym) {
                "f" -> value * 9 / 5 + 32
                else -> value + 273.15
            }
        }
        "f" -> {
            when (finalUnit.acronym) {
                "c" -> (value - 32) * 5 / 9
                else -> (value + 459.67) * 5 / 9
            }
        }
        else -> {
            when (finalUnit.acronym) {
                "c" -> value - 273.15
                else -> value * 9 / 5 - 459.67
            }
        }
    }
}

private fun isPossibleToConvert(firstUnit: Unit?, secondUnit: Unit?): Boolean? = firstUnit?.type == secondUnit?.type

data class Result(val result: Double, val initUnit: Unit?, val finalUnit: Unit?, var exitFlag: Boolean)

private fun readData(unitsMap: Map<String, Unit>): Result {
    println("Enter what you want to convert (or Exit):")
    val scanner = Scanner(System.`in`)
    var result = Result(-1.0, null, null, false)
    val next = scanner.next().toLowerCase()
    if (next == "exit") {
        result.exitFlag = true
    } else {
        val value = next.toDouble()
        var initialUnitName = scanner.next().toLowerCase()
        if (initialUnitName.startsWith("degree")) {
            initialUnitName += " " + scanner.next().toLowerCase()
        }
        val initialUnit = findUnit(initialUnitName, unitsMap)
        scanner.next()
        var finalUnitName = scanner.next().toLowerCase()
        if (finalUnitName.startsWith("degree")) {
            finalUnitName += " " + scanner.next().toLowerCase()
        }
        val finalUnit = findUnit(finalUnitName, unitsMap)
        result = Result(value, initialUnit, finalUnit, false)
    }
    return result
}

private fun findUnit(string: String, unitsMap: Map<String, Unit>): Unit? {
    return when (string) {
        "m", "meter", "meters" -> unitsMap["m"]
        "km", "kilometer", "kilometers" -> unitsMap["km"]
        "cm", "centimeter", "centimeters" -> unitsMap["cm"]
        "mm", "millimeter", "millimeters" -> unitsMap["mm"]
        "mi", "mile", "miles" -> unitsMap["mi"]
        "yd", "yard", "yards" -> unitsMap["yd"]
        "ft", "foot", "feet" -> unitsMap["ft"]
        "in", "inch", "inches" -> unitsMap["in"]
        "g", "gram", "grams" -> unitsMap["g"]
        "kg", "kilogram", "kilograms" -> unitsMap["kg"]
        "mg", "milligram", "milligrams" -> unitsMap["mg"]
        "lb", "pound", "pounds" -> unitsMap["lb"]
        "oz", "ounce", "ounces" -> unitsMap["oz"]
        "degree celsius", "degrees celsius", "dc", "c" -> unitsMap["c"]
        "degree fahrenheit", "degrees fahrenheit", "df", "f" -> unitsMap["f"]
        "kelvin", "kelvins", "k" -> unitsMap["k"]
        else -> null
    }
}

private fun initUnitsMap(): Map<String, Unit> {
    return mapOf(
        "m" to Unit(UnitTypes.LENGTH, "m", "meter", "meters", 1.0),
        "km" to Unit(UnitTypes.LENGTH, "km", "kilometer", "kilometers", 1000.0),
        "cm" to Unit(UnitTypes.LENGTH, "cm", "centimeter", "centimeters", 0.01),
        "mm" to Unit(UnitTypes.LENGTH, "mm", "millimeter", "millimeters", 0.001),
        "mi" to Unit(UnitTypes.LENGTH, "mi", "mile", "miles", 1609.35),
        "yd" to Unit(UnitTypes.LENGTH, "yd", "yard", "yards", 0.9144),
        "ft" to Unit(UnitTypes.LENGTH, "ft", "foot", "feet", 0.3048),
        "in" to Unit(UnitTypes.LENGTH, "in", "inch", "inches", 0.0254),
        "g" to Unit(UnitTypes.WEIGHT, "g", "gram", "grams", 1.0),
        "kg" to Unit(UnitTypes.WEIGHT, "kg", "kilogram", "kilograms", 1000.0),
        "mg" to Unit(UnitTypes.WEIGHT, "mg", "milligram", "milligrams", 0.001),
        "lb" to Unit(UnitTypes.WEIGHT, "lb", "pound", "pounds", 453.592),
        "oz" to Unit(UnitTypes.WEIGHT, "oz", "ounce", "ounces", 28.3495),
        "c" to Unit(UnitTypes.TEMPERATURE, "c", "degree Celsius", "degrees Celsius", 1.0),
        "f" to Unit(UnitTypes.TEMPERATURE, "f", "degree Fahrenheit", "degrees Fahrenheit", 1.0),
        "k" to Unit(UnitTypes.TEMPERATURE, "k", "Kelvin", "Kelvins", 1.0)
    )
}