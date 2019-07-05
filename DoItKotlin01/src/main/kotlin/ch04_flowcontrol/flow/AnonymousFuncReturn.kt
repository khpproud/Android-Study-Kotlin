package ch04_flowcontrol.flow

// 익명함수를 사용한 반환
fun main() {
    retFunc3()

    // 람다식과 익명 함수를 함수에 할당할 때 주의할 점
    fun greet(): () -> Unit = fun() { println("Hello") }
    greet()()
}

fun retFunc3() {
    println("start of retFunc3")
    inlineLambda(13, 3, fun (a, b) {
        val result = a + b
        if (result > 10) return
        println("result : $result")
    })
    println("end of retFunc3")
}