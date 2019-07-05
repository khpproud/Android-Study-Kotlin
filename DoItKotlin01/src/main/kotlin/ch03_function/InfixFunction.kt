package ch03_function

fun main() {
    // 일반 표현법
    val multi1 = 3.multiply(10)
    println(multi1)

    // 중위 표현법
    val multi2 = 3 multiply 10
    println(multi2)
}

// Int 확장함수 multiply()를 infix 키워드로 중위함수로 만듬
infix fun Int.multiply(x: Int): Int = this * x