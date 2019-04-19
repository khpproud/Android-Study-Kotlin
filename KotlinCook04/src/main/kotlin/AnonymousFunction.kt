package chap04

// 익명함수로 작업 예
fun main() {
    var funcMultiply = fun (a: Int, b: Int): Int {
        return a * b
    }
    println(funcMultiply(3, 4))
    val sayHi = fun (name: String): Unit = println("Hi $name")
    sayHi("Lee")

    performMath2(3, 4, fun(a: Int, b: Int): Int = a * b)
}

fun performMath2(a: Int, b: Int, mathFunc: (Int, Int) -> Int): Unit {
    println("value of calculation: ${mathFunc(a, b)}")
}