package chap02.first

// DSL 을 생성할 수 있는 예 : 부정 실행문
fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition) block()
}

fun main() {
    val securityCheck = false
    unless(securityCheck) {
        println("Not available!!!")
    }
}