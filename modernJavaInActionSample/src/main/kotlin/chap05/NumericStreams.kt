package chap05

import Dish
import menu
import java.util.*
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.math.sqrt

object NumericStreams {
    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = listOf(3, 4, 5, 1, 2)
        Arrays.stream(numbers.toTypedArray()).forEach(::println)
        println("-----")

        val calories = menu.stream()
            .mapToInt(Dish::calories)
            .sum()
        println("Total calories: $calories")

        // Max and OptionalInt
        val maxCalories: OptionalInt = menu.stream()
            .mapToInt(Dish::calories)
            .max()
        maxCalories.ifPresent(::println)

        // Numeric ranges
        val evenNumbers = IntStream.rangeClosed(1, 100)
            .filter { n -> n % 2 == 0 }
        println(evenNumbers.count())

        // Pythagorean Triples
        // a^2 + b^2 = c^2 => Math.sqrt(a*a + b*b) % 1 == 0
        val pythagoreanTriples1: Stream<IntArray> =
            IntStream.rangeClosed(1, 100).boxed()
                .flatMap { a ->
                    IntStream.rangeClosed(a, 100)
                        .filter { b -> sqrt((a * a + b * b).toDouble()) % 1 == 0.0 }
                        .boxed()
                        .map { b -> intArrayOf(a, b, sqrt((a * a + b * b).toDouble()).toInt()) }
                }
        pythagoreanTriples1.limit(5).forEach { t -> println("${t[0]}, ${t[1]}, ${t[2]}") }

        val pythagoreanTriples2: Stream<IntArray> =
            IntStream.rangeClosed(1, 100).boxed()
                .flatMap { a ->
                    IntStream.rangeClosed(a, 100)
                        .mapToObj {
                            b -> doubleArrayOf(a.toDouble(), b.toDouble(), sqrt((a * a + b * b).toDouble()))
                        }
                        .filter { t -> t[2] % 1 == 0.0 }
                        .map { t-> intArrayOf(t[0].toInt(), t[1].toInt(), t[2].toInt())}
                }

        pythagoreanTriples2.limit(5).forEach { t -> println("${t[0]}, ${t[1]}, ${t[2]}") }

    }
}