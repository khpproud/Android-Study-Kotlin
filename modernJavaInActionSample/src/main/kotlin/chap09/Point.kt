package chap09

import java.util.Comparator.comparing

data class Point(val x: Int, val y: Int) {

    companion object {
        val compareByXAndThenY: Comparator<Point> = comparing(Point::x).thenComparing(Point::y)
    }

    fun moveRightBy(x: Int): Point {
        return Point(this.x + x, y)
    }
}

fun <T> filter(list: List<T>, p: (T) -> Boolean): List<T> {
    return list.filter(p)
}