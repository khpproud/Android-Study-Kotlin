package chap05

import Dish
import menu
import java.util.Comparator.comparing

object Slicing {
    @JvmStatic
    fun main(args: Array<String>) {
        val sortedDishes = menu.sortedWith(comparing(Dish::calories))

        // TakeWhile
        sortedDishes.stream()
            .takeWhile { dish -> dish.calories < 320 }
            .forEach(::println)
        println("-----")

        // DropWhile
        sortedDishes.stream()
            .dropWhile { dish -> dish.calories < 320 }
            .forEach(::println)
        println("-----")

        // Filtering
        sortedDishes.stream()
            .filter { dish -> dish.type == Dish.Type.MEAT }
            .limit(2)
            .forEach(::println)
    }
}