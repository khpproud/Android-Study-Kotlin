package chap06

import Dish
import menu
import java.util.stream.Collectors.counting
import java.util.stream.Collectors.reducing
import java.util.stream.Stream

object Reducing {
    @JvmStatic
    fun main(args: Array<String>) {
        println("How many dishes: ${countDishes()}")
        println("Total calories in menu: ${calculateTotalCalories()}")
        println("Total calories in menu: ${calculateTotalCaloriesWithMethodReference()}")
        println("Total calories in menu: ${calculateTotalCaloriesWithoutCollectors()}")
        println("Total calories in menu: ${calculateTotalCaloriesUsingSum()}")

        // toList -> reducing example
        val stream: Stream<Int> = listOf(1, 2, 3, 4, 5, 6).stream()
        val numbers: List<Int> = stream.reduce(
            arrayListOf<Int>(), { l: ArrayList<Int>, e: Int ->
                l.add(e)
                l},
            { l1: ArrayList<Int>, l2: ArrayList<Int> ->
                l1.addAll(l2)
                l1
            }
        )
        println(numbers)
    }

    private fun calculateTotalCalories(): Int {
        return menu.stream().collect(reducing(0, Dish::calories, { i, j -> i + j } ))
    }

    private fun calculateTotalCaloriesWithMethodReference(): Int {
        return menu.stream().collect(reducing(0, Dish::calories, Int::plus))
    }

    private fun calculateTotalCaloriesWithoutCollectors(): Int {
        return menu.stream().map(Dish::calories).reduce(0, Int::plus)
    }

    private fun calculateTotalCaloriesUsingSum(): Int {
        return menu.stream().mapToInt(Dish::calories).sum()
    }

//    private fun countDishes() = menu.stream().count()
    // or
    private fun countDishes() = menu.stream().collect(counting())
}