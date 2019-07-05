package ch04_flowcontrol.flow

// 라벨을 사용한 람다식의 반환
fun main() {
    retFunc2()
}

fun noInlineLambda(a: Int, b: Int, out: (Int, Int) -> Unit) {
    out(a, b)
}

fun retFunc2() {
    println("start of retFunc2")
    noInlineLambda(13, 3) { a, b ->
        val result = a + b
        if (result > 10) return@noInlineLambda
        println("result : $result")
    }
    println("end of recFunc2")
}