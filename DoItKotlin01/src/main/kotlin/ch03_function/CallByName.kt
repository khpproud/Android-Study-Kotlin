package ch03_function

// 람다식 이름을 사용해 호출
fun main() {
    val result = callByName(otherLambda)        // 람다식 이름으로 호출
    println(result)
}

fun callByName(b: () -> Boolean): Boolean {             // 람다식 자료형으로 선언된 매개변수
    println("callByName function")
    return b()
}

val otherLambda: () -> Boolean = {
    println("otherLambda function")
    true
}