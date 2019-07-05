package ch03_function

// 인라인 함수 간단란 예
fun main() {
    // 인라인 함수 shortFunc()의 내용이 복사되어 shortFunc 으로 들어감
    shortFunc(3) { println("First Call: $it") }
    shortFunc(5) { println("Second Call : $it") }
}

inline fun shortFunc(a: Int, out: (Int) -> Unit) {
    println("Before calling out()")
    out(a)
    println("After calling out()")
}