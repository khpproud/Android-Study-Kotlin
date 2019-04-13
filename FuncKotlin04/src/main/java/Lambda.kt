package chap04

// 코틀린에서 람다(익명 함수) 사용 예
fun invokeSomeStuff(doSomeStuff: () -> Unit) {
    doSomeStuff()
}

fun main() {
    invokeSomeStuff { println("doSomeStuff() 호출됨") }
}