package ch03_function

// 최상위 함수와 지역 함수
// 최상위 함수는 함수 선언 위치에 관계 없이 사용 가능
fun a() = b()
fun b() = println("B")

// 지역 함수는 선언이 먼저 되어야 사용 가능
fun c() {
//    fun d() = f()
    fun f() = println("e")
}

fun main() {
    a()             // 최상위 함수는 어디서든 호출할 수 있음
//    f()           // c의 지역함수 f는 범위 밖에서 사용할 수 없음
}