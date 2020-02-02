package chap06

import Dish
import menu
import java.util.*
import java.util.Comparator.comparingInt
import java.util.stream.Collectors.*

object Partitioning {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Dishes partitioned by vegetarian: " + partitionByVegetarian())
        println("Vegetarian Dishes by type: " + vegetarianDishesByType())
        println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian())
    }

    private fun partitionByVegetarian(): Map<Boolean, List<Dish>> {
        return menu.stream().collect(partitioningBy(Dish::vegetarian))
    }

    private fun vegetarianDishesByType(): Map<Boolean, Map<Dish.Type, List<Dish>>> {
        return menu.stream().collect(partitioningBy(Dish::vegetarian, groupingBy(Dish::type)))
    }

    private fun mostCaloricPartitionedByVegetarian(): Map<Boolean, Dish> {
        return menu.stream().collect(partitioningBy(Dish::vegetarian,
            collectingAndThen(maxBy(comparingInt(Dish::calories)), Optional<Dish>::get)))
    }
}