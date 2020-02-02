package chap05

import Dish
import menu
import kotlin.math.max
import kotlin.math.min

object Reducing {
    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = listOf(3, 4, 5, 1, 2, 6)
        val sum = numbers.stream().reduce(0) { a, b -> a + b }
        println(sum)
        val sum2 = numbers.stream().reduce(0, Int::plus)
        println(sum2)

        val max = numbers.stream().reduce(0, ::max)
        println(max)

        numbers.stream().reduce(::min).ifPresent(::println)

        val totalCalories = menu.stream()
            .map(Dish::calories)
            .reduce(0, Int::plus)
        println("Total calories of dishes: $totalCalories")

    }
}