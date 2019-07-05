package ch04_flowcontrol.flow

// inline 을 사용한 람다식의 반환
fun main() {
    retFunc()
}

inline fun inlineLambda(a: Int, b: Int, out: (Int, Int) -> Unit) {
    out(a, b)
}

fun retFunc() {
    println("start of retFunc")
    inlineLambda(12, 3) { a, b ->
        val result = a + b
        if (result > 10) return             // 비지역 반환(non-local return)되어 retFunc()가 종료됨
        println("result: $result")
    }
    println("end of retFunc")
}