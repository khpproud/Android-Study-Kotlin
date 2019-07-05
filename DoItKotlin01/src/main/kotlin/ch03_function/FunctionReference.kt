package ch03_function

// 참조에 의한 호출 방식으로 일반 함수 호출하기
fun main() {
    // 인자와 반환값이 있는 함수
    val res1 = funcParam(3, 2, ::sum)
    println(res1)

    // 인자가 없는 함수
    hello(::text)

    // 일반 변수에 값처럼 할당
    val likeLambda = ::sum
    println(likeLambda(6, 6))
}

fun text(a: String, b: String) = "Hi! $a $b"

fun funcParam(a: Int, b: Int, c:(Int, Int) -> Int): Int {
    return c(a, b)
}

fun hello(body: (String, String) -> String): Unit {
    println(body("Hello", "Kotlin!"))
}