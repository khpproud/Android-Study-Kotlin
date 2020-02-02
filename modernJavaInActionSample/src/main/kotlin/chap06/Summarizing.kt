package chap06

import Dish
import menu
import java.util.*
import java.util.function.BinaryOperator
import java.util.stream.Collectors.*

object Summarizing {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Number of dishes: ${howMayDishes()}")
        println("The most caloric dish is ${findMostCaloricDish()}")
        println("The most caloric dish is ${findMostCaloricDishUsingComparator()}")
        println("Total calories in menu ${calculateTotalCalories()}")
        println("Average calories in menu ${calculateAverageCalories()}")
        println("Menu statistics: ${calculateMenuStatistics()}")
        println("Short menu: ${getShortMenu()}")
        println("Short menu comma separated: ${getShortMenuCommaSeparated()}")
    }

    private fun howMayDishes() = menu.stream().collect(counting())

    private fun findMostCaloricDish(): Dish {
        // collect reducing
        return menu.stream().collect(reducing
            { d1, d2 -> if(d1.calories > d2.calories) d1 else d2}).get()
        // reduce
//        return menu.stream().reduce { d1, d2 -> if(d1.calories > d2.calories) d1 else d2 }.get()
    }

    private fun findMostCaloricDishUsingComparator(): Dish {
        val dishCaloriesComparator = Comparator.comparingInt(Dish::calories)
        val moreCaloricOf: BinaryOperator<Dish> = BinaryOperator.maxBy(dishCaloriesComparator)
//        return menu.stream().collect(reducing(moreCaloricOf)).get()
        // or
        return menu.stream().collect(maxBy(dishCaloriesComparator)).get()
    }

    private fun calculateTotalCalories(): Int {
        return menu.stream().collect(summingInt(Dish::calories))
    }

    private fun calculateAverageCalories(): Double {
        return menu.stream().collect(averagingInt(Dish::calories))
    }

    private fun calculateMenuStatistics(): IntSummaryStatistics {
        return menu.stream().collect(summarizingInt(Dish::calories))
    }

    private fun getShortMenu(): String {
        return menu.stream().map(Dish::name).collect(joining())
    }

    private fun getShortMenuCommaSeparated(): String {
        return menu.stream().map(Dish::name).collect(joining(", "))
    }

}