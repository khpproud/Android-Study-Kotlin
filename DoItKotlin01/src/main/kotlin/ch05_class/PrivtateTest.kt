package ch05_class

// private 가시성 테스트
private class PrivateClass {
    private var i = 1
    private fun privateFunc() {
        i += 1
    }
    fun access() {
        privateFunc()                       // 접근 허용
    }
}

class OuterClass {
//    val opc = PrivateClass()                // 불가 - 프로퍼티 opc 는 private 이 되어야 함
    private val opc = PrivateClass()        // 같은 private 가시성 지시자만 가능
    fun test() {
        val pc = PrivateClass()             // 생성 가능
    }
}
fun main() {
    val pc = PrivateClass()                 // 생성 가능
//    pc.i                                    // 접근 불가
//    pc.privateFunc()                        // 접근 불가
}

fun topFunction() {
    val tpc = PrivateClass()                // 객체 생성 가능
}