package ch02_val_type_op

fun main() {
    var number1 = 12
    var number2 = 25

    val resultOr = number1 or number2
    println(resultOr)

    val resultAnd = number1 and number2
    println(resultAnd)

    val resultXor = number1 xor number2
    println(resultXor)

    // SwapWithXor
    number1 = number1 xor number2
    number2 = number1 xor number2
    number1 = number1 xor number2

    println("number1 : $number1")
    println("number2 : $number2")
}