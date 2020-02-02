package chap19

fun main() {
    val convertCtoF = curriedConverter(9.0 / 5, 32.0)
    val convertUSDtoGBP = curriedConverter(0.6, .0)
    val convertKmToMi = curriedConverter(0.6214, .0)

    println(format("24 °C = %.2f °F", convertCtoF(24.0)))
    println(format("US$100 = £%.2f", convertUSDtoGBP(100.0)))
    println(format("20 km = %.2f mile", convertKmToMi(20.0)))

    val convertFtoC = expandedCurriedConverter(-32.0, 5.0 / 9, .0)
    println(format("98.6 °F = %.2f °C", convertFtoC(98.6)))
}

private fun converter(x: Double, y: Double, z: Double) = x * y + z

private fun curriedConverter(y: Double, z: Double) = { x: Double -> x * y + z }

private fun expandedCurriedConverter(w: Double, y: Double, z: Double) = { x: Double -> (x + w) * y + z }

private fun format(str: String, value: Double) = String.format(str, value)