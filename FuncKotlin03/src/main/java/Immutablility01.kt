package chap03.immutable

// 함수형 프로그래밍의 불변성 예
fun main() {
    var x: String = "hello"
    var y = x.capitalize()

    // x의 값은 변경되지 않고 변경되지 않은 채로 남음
    println("x = $x, y = $y")
}