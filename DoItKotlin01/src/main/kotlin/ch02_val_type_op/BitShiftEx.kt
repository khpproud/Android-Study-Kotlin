package ch02_val_type_op

fun main() {
    var x = 4
    var y = 0b0000_1010         // 10진수 10
    var z = 0x0F                // 10진수 15

    println("x shl 2 -> ${x shl 2}")            // x * 2^2
    println("x.inv() -> ${x.inv()}")            // inv() => (- x - 1)
    println("x -> ${x.toString(radix = 2)}")
    println("x.inv() -> ${(- x - 1).toString(radix = 2)}")

    println("y shr 2 -> ${y / 4}, ${y shr 2}")
    println("x shl 4 -> ${x * 16}, ${x shl 4}")
    println("z shl 4 -> ${z * 16}, ${z shl 4}")

    x = 64
    println("x shr 4 -> ${x / 16}, ${x shr 4}")

    // unsigned shift
    val number1 = 5
    val number2 = -5

    println(number1 shr 1)
    println(number1 ushr 1)         // 변화 없음
    println("-5 -> ${String.format("%s", Integer.toBinaryString(number2))}")
    println("number2 shr 1 -> ${number2 shr 1}, binString :  ${Integer.toBinaryString(number2 shr 1)}" )
    val number3 = number2 ushr 1
    println("number2 ushr 1 -> $number3, binString : ${Integer.toBinaryString(number3)}")
}