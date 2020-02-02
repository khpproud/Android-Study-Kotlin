package chap06

import Dish
import dishTags
import menu
import java.util.*
import java.util.Comparator.comparingInt
import java.util.stream.Collectors.*

object Grouping {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Dishes grouped by type: " + groupDishesByType())
        println("Dish names grouped by type: " + groupDishNamesByType())
        println("Dish tags grouped by type: " + groupDishTagsByType())
        println("Caloric dishes grouped by type: " + groupCaloricDishesByType())
        println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel())
        println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel())
        println("Count dishes in groups: " + countDishesInGroups())
        println("Most caloric dishes by type: " + mostCaloricDishesByType())
        println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals())
        println("Sum calories by type: " + sumCaloriesByType())
        println("Caloric levels by type: " + caloricLevelsByType())
    }

    private fun groupDishesByType(): Map<Dish.Type, List<Dish>> {
        return menu.stream().collect(groupingBy(Dish::type))
    }

    private fun groupDishNamesByType(): Map<Dish.Type, List<String>> {
        return menu.stream().collect(groupingBy(Dish::type, mapping(Dish::name, toList<String>())))
    }

    private fun groupDishTagsByType(): Map<Dish.Type, Set<String>> {
        return menu.stream().collect(
            groupingBy(
                Dish::type,
                flatMapping({ dish: Dish -> dishTags[dish.name]!!.stream() }, toSet<String>())
            )
        )
    }

    private fun groupCaloricDishesByType(): Map<Dish.Type, List<Dish>> {
//        return menu.stream().filter { dish -> dish.calories > 500 }.collect(groupingBy(Dish::type))
        return menu.stream().collect(
            groupingBy(
                Dish::type,
                filtering({ dish -> dish.calories > 500 }, toList<Dish>())
            )
        )
    }

    private fun groupDishesByCaloricLevel(): Map<Dish.CaloricLevel, List<Dish>> {
        return menu.stream().collect(
            groupingBy { dish ->
                when {
                    dish.calories <= 400 -> {
                        Dish.CaloricLevel.DIET
                    }
                    dish.calories <= 700 -> {
                        Dish.CaloricLevel.NORMAL
                    }
                    else -> {
                        Dish.CaloricLevel.FAT
                    }
                }
            }
        )
    }

    private fun groupDishedByTypeAndCaloricLevel(): Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> {
        return menu.stream().collect(
            groupingBy(
                Dish::type,
                groupingBy({ dish ->
                    when {
                        dish.calories <= 400 -> Dish.CaloricLevel.DIET
                        dish.calories <= 700 -> Dish.CaloricLevel.NORMAL
                        else -> Dish.CaloricLevel.FAT
                    }
                }, toList<Dish>())
            )
        )
    }

    private fun countDishesInGroups(): Map<Dish.Type, Long> {
        return menu.stream().collect(groupingBy(Dish::type, counting()))
    }

    private fun mostCaloricDishesByType(): Map<Dish.Type, Optional<Dish>> {
        return menu.stream().collect(
            groupingBy(
                Dish::type,
                maxBy(comparingInt(Dish::calories))
            )
        )
        // Or
//        return menu.stream().collect(groupingBy(Dish::type,
//            reducing { d1: Dish, d2: Dish -> if (d1.calories > d2.calories) d1 else d2 } ))
    }

    private fun mostCaloricDishesByTypeWithoutOptionals(): Map<Dish.Type, Dish> {
        return menu.stream().collect(
            groupingBy(
                Dish::type,
                collectingAndThen(maxBy(comparingInt(Dish::calories)), Optional<Dish>::get)
            )
        )
    }

    private fun sumCaloriesByType(): Map<Dish.Type, Int> {
        return menu.stream().collect(
            groupingBy(
                Dish::type,
                summingInt(Dish::calories)
            )
        )
    }

    private fun caloricLevelsByType(): Map<Dish.Type, Set<Dish.CaloricLevel>> {
        return menu.stream().collect(
            groupingBy(
                Dish::type,
                mapping({ dish ->
                    when {
                        dish.calories <= 400 -> Dish.CaloricLevel.DIET
                        dish.calories <= 700 -> Dish.CaloricLevel.NORMAL
                        else -> Dish.CaloricLevel.FAT
                    }
                }, toSet<Dish.CaloricLevel>())
            )
        )
    }
}