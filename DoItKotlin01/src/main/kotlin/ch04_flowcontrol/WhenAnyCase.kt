package ch04_flowcontrol

import java.math.BigInteger

// 다양한 자료형의 인자 받기
fun main() {
    case("Hello")
    case(1)
    case(System.currentTimeMillis())
    case(BigInteger("1"))
}

fun case(obj: Any) {
    when (obj) {
        1 -> println("Int : $obj")
        "Hello" -> println("String: $obj")
        is Long -> println("Long : $obj")
        !is String -> println("Not a String")
        else -> println("Unknown")
    }
}