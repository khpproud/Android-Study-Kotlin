package chap05

// 고차 함수를 정의할 때 함다에 대한 파라미터의 이름을 짓지 않음
fun high(f: (Int, String) -> Unit) {
    f(1, "Romeo")
}

// 람다의 파라미터 이름을 추가하는 것은 가능
fun high2(f: (age: Int, name: String) -> Unit) {
    f(1, "Romeo")

    // 컴파일 에러 : 람다의 호출에는 적용 불가
    //f(age = 3, name = "Romeo")
}

fun main() {
    high { q, w -> println("q : $q, name : $w") }
}