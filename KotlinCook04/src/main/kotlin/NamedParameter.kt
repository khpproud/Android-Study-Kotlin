package chap04

// 명명된 파라미터 사용 예
fun foo(a: Int = 9, b: Double = 0.1, c: String = "some default value") {
    println("a = $a, b = $b, c = $c")
}

fun main() {
    foo(b = 0.9)
    foo(a = 1, c = "Custom string")
    foo(1, 0.1)

    // 아래는 Compile error : 명명된 인수 다음에 위치 인수 선언 허용 안됨
    //foo(a = 3, 0.4)
}