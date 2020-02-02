package chap09

import java.util.*
import java.util.stream.Collectors.toList
import java.util.stream.Stream

object Debugging {
    @JvmStatic
    fun main(args: Array<String>) {
        val points: List<Point?> = listOf(Point(10, 10), null)
        // Caused error (NullPointerEx: chap09.Debugging$main$1.apply(...))
//        points.stream().map { p -> p!!.x }.forEach(::println)

        // Peek example
        val result = Stream.of(2, 3, 4, 5)
            .peek { x -> println("taking from stream: $x") }
            .map { x -> x + 17 }
            .peek { x -> println("after map: $x") }
            .filter { x -> x % 2 == 0 }
            .peek { x -> println("after filter: $x") }
            .limit(3)
            .peek { x -> println("after limit: $x") }
            .collect(toList())

        println(result)
    }

    data class Point(val x: Int, val y: Int)
}