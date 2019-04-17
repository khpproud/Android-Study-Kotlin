package chap01

// 코틀린에서의 비트 연산 예

fun main() {
    val a = 2
    val b = 3

    println("2 and 3 = ${a and b}")
    println("2 or 3 = ${a or b}")
    println("2 xor 3 = ${a xor b}")
    println("2 inv() = ${a.inv()}")

    println("5 shl 1 = ${5 shl 1}")
    println("5 shr 1 = ${5 shr 1}")

}