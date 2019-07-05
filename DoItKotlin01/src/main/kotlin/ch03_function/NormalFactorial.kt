package ch03_function

// 일반적인 책토리알 재귀 함수
fun main() {
    val number: Int = 4
    val result: Long

    result = factorial(number)
    println("Factorial: $number -> $result")
}

fun factorial(n: Int): Long {
    return if (n == 1) n.toLong() else n * factorial(n - 1)
}