package chap04

import Dish
import menu
import java.util.Comparator.comparing
import java.util.stream.Collectors.toList
import java.util.stream.Stream

object StreamBasic {
    @JvmStatic
    fun main(args: Array<String>) {
        val names = listOf("Java8", "Lambdas", "In", "Action")
        val s: Stream<String> = names.stream()
        s.forEach(::println)
        // Streams can be consumed only once.
//        s.forEach(::println)
        println("----------")

        // Before Java 8
        getLowCaloricDishesNamesBeforeJava8(menu).forEach(::println)

        println("----------")

        // Java 8
        getLowCaloricDishesNamesInJava8(menu).forEach(::println)
    }

    fun getLowCaloricDishesNamesBeforeJava8(dishes: List<Dish>): List<String> {
        val lowCaloricDishes = arrayListOf<Dish>()
        for (d in dishes) {
            if (d.calories < 400) {
                lowCaloricDishes.add(d)
            }
        }
        val lowCaloricDishesName = arrayListOf<String>()
        val lowCaloricDishesSorted = lowCaloricDishes.sortedWith(object : Comparator<Dish> {
            override fun compare(o1: Dish, o2: Dish): Int {
                return o1.calories.compareTo(o2.calories)
            }
        })
        for (d in lowCaloricDishesSorted) {
            lowCaloricDishesName.add(d.name)
        }
        return lowCaloricDishesName
    }

    fun getLowCaloricDishesNamesInJava8(dishes: List<Dish>): List<String> {
        return dishes.stream()
            .filter { d -> d.calories < 400 }
            .sorted(comparing(Dish::calories))
            .map(Dish::name)
            .collect(toList())
    }
}