package unitConverter

import java.util.*

fun main(args: Array<String>) {
    val unitsMap = initUnitsMap()
    while (true) {
        val (value, initialUnit, finalUnit, exitFlag) = readData(unitsMap)
        if (exitFlag) {
            break
        }
        if (value >= 0 && isPossibleToConvert(initialUnit!!, finalUnit!!)) {
            val correctInitialUnit = if (value == 1.0) initialUnit.single else initialUnit.multiple
            val result = value * initialUnit.factor / finalUnit.factor
            val correctMetersUnit = if (result == 1.0) finalUnit.single else finalUnit.multiple
            println("$value $correctInitialUnit is $result $correctMetersUnit")
        }
    }
}

private fun isPossibleToConvert(firstUnit: Unit, secondUnit: Unit): Boolean = firstUnit.type == secondUnit.type

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
        val initialUnit = findUnit(scanner.next().toLowerCase(), unitsMap)
        scanner.next()
        val finalUnit = findUnit(scanner.next().toLowerCase(), unitsMap)
        if (value >= 0 && initialUnit != null && finalUnit != null) {
            result = Result(value, initialUnit, finalUnit, false)
        }
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
        "oz" to Unit(UnitTypes.WEIGHT, "oz", "ounce", "ounces", 28.3495)
    )
}