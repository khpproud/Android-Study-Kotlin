package ch03_function

// 변수에 할당하는 람다식 함수
fun main() {
    var result: Int
    val multi = {x: Int, y: Int -> x * y }          // 일반 변수에 람다식 할당
    result = multi(10, 20)
    println(result)

    val greet = { println("Hello World") }
    val square = { x: Int -> x * x }
    val nestedLambda = { greet() }

    nestedLambda()

    // 인자와 반환 값이 없는 람다식 함수
    val out: () -> Unit = { println("Hello Kotlin!") }
    out()
    val newVal = out
    newVal()
}