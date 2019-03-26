package four

// 단일 매개변수를 갖는 메소드에 대해서만 중위 표기를 허용
data class Point2(val x: Int, val y: Int) {
    // infix 한정자를 이용해 선언
    infix fun moveRight(shift: Int) = Point2(x + shift, y)
}

fun main() {
    // 사용법
    val pointA = Point2(1, 4)
    val pointB = pointA moveRight 2
    println(pointB)
}