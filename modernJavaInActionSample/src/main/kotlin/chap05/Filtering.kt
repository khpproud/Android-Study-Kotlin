package chap05

import Dish
import menu
import java.util.stream.Collectors.toList

object Filtering {
    @JvmStatic
    fun main(args: Array<String>) {
        // Filtering with Predicate
        val vegetarianMenu: List<Dish> =
            menu.stream()
                .filter(Dish::vegetarian)
                .collect(toList())
        vegetarianMenu.forEach(::println)
        println("-----")

        // Filtering with unique elements
        val numbers = listOf(1, 2, 1, 3, 3, 2, 4, 2)
        numbers.stream()
            .filter { i -> i % 2 == 0 }
            .distinct()
            .forEach(::println)
        println("-----")

        // Truncate a stream
        menu.stream()
            .filter { d -> d.calories > 300 }
            .limit(3)
            .forEach(::println)
        println("-----")

        // Skipping elements
        menu.stream()
            .filter { d -> d.calories < 500 }
            .skip(2)
            .forEach(::println)
    }
}