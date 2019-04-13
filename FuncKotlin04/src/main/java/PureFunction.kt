package chap04

// 순수 함수의 예
fun addNumbers(a: Int = 0, b: Int = 0): Int {
    return a + b
}

fun main() {
    println("addNumbers(10,15) : ${addNumbers(10,15)}")
}