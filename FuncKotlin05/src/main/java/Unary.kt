package chap05

// Unary 연산자
operator fun Wolf.not() = "$name 은 화났다!!!"

fun main() {
    val talbot = Wolf("Talbot")
    println(!talbot)
}