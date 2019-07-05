package ch03_function

// crossinline 으로 비지역 반환 금지하기
fun main() {
    shortFunc3(3) {
        println("First call : $it")
        // return 사용 불가
    }
}

inline fun shortFunc3(a: Int, crossinline out: (Int) -> Unit) {
    println("Before calling out()")
    nestedFunc { out(a) }
    println("After calling out")
}

fun nestedFunc(body: () -> Unit) {
    body()
}