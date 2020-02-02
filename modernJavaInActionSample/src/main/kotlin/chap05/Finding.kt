package chap05

import Dish
import menu
import java.util.*

object Finding {
    @JvmStatic
    fun main(args: Array<String>) {
        if (isVegetarianFriendlyMenu()) {
            println("Vegetarian friendly")
        }

        println(isHealthyMenu())
        println(isHealthyMenu2())

        findVegetarianDish().ifPresent { println(it.name) }
    }

    fun isVegetarianFriendlyMenu() = menu.stream().anyMatch(Dish::vegetarian)

    fun isHealthyMenu() = menu.stream().allMatch { dish -> dish.calories < 1000 }

    fun isHealthyMenu2() = menu.stream().noneMatch { dish -> dish.calories >= 1000 }

    fun findVegetarianDish(): Optional<Dish> = menu.stream()
        .filter(Dish::vegetarian)
        .findAny()
}