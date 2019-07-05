package chp07_variable_class

// '+' 연산자의 오버로딩
class Point(var x: Int = 0, var y : Int = 10) {
    operator fun plus(p: Point): Point {
        return Point(x + p.x, y + p.y)
    }

    operator fun dec(): Point {
        return Point(--x, --y)
    }

    operator fun unaryMinus() = Point(-x, -y)

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }

}

fun main() {
    val p1 = Point(3, -8)
    val p2 = Point(2, 9)

    var point = p1 + p2
    println("point = (${point.x}, ${point.y})")

    --point
    println("point = (${point.x}, ${point.y})")

    println(-point)
}