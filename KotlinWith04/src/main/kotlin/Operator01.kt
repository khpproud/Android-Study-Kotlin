package four

data class Point(var x: Double, var y: Double) {
    operator fun plus(point: Point) = Point(x + point.x, y + point.y)
    // 동일한 이름으로 다른 매개변수 형식을 사용하는 연산자 정의
    operator fun plus(vector: Double) = Point(x + vector, y + vector)
    operator fun times(other: Int) = Point(x * other, y * other)
}

fun main() {
    // 간단한 사용법
    var p1 = Point(2.9, 5.0)
    var p2 = Point(2.0, 7.5)

    println(p1 + p2)
    println(p1 * 3)
    println(p1 + 3.1)

    // 복합 연산자도 자동으로 지원 (Unit을 반환하므로 기존 객체 수정)
    p1 += p2
    println(p1)

    // 기본 연산자는 항상 새로운 객체의 인스턴스 반환
    val p3 = p1 + p2
    println(p3)
}