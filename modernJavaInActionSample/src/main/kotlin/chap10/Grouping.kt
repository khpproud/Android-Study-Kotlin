package chap10

import Dish
import chap10.Grouping.GroupingBuilder.Companion.groupOn
import menu
import java.util.function.Function
import java.util.stream.Collector
import java.util.stream.Collectors.groupingBy

object Grouping {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Dishes grouped by type and caloric level: ${groupDishedByTypeAndCaloricLevel2()}")
        println("Dishes grouped by type and caloric level: ${groupDishedByTypeAndCaloricLevel3()}")
        println("Dishes grouped by type and caloric level: ${groupDishedByTypeAndCaloricLevel4()}")
        println("Dishes grouped by type and caloric level: ${groupDishedByTypeAndCaloricLevel5()}")
    }

    private fun groupDishedByTypeAndCaloricLevel2(): Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> {
        return menu.stream().collect(
            twoLevelGroupingBy(Function(Dish::type), Function { dish: Dish -> getCaloricLevel(dish)}))
    }

    private fun groupDishedByTypeAndCaloricLevel3(): Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> {
        return menu.stream().collect(
            twoLevelGroupingBy(Dish::type) { dish: Dish -> getCaloricLevel(dish) })
    }

    private fun groupDishedByTypeAndCaloricLevel4(): Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> {
        val c: Collector<in Dish, *, Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>>> =
            groupOn(Function { dish: Dish -> getCaloricLevel(dish)}).after(Function(Dish::type)).get()
        return menu.stream().collect(c)
    }

    private fun groupDishedByTypeAndCaloricLevel5(): Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> {
        val c: Collector<in Dish, *, Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>>> =
            groupOn(::getCaloricLevel).after(Dish::type).get()
        return menu.stream().collect(c)
    }

    // Java8 Function used
    fun <A, B, T> twoLevelGroupingBy(f1: Function<in T, out A>, f2: Function<in T, out B>)
            : Collector<T, *, Map<A, Map<B, List<T>>>> {
        return groupingBy(f1, groupingBy(f2))
    }

    // Kotlin lambda style
    fun <A, B, T> twoLevelGroupingBy(f1: (T) -> A, f2: (T) -> B): Collector<T, *, Map<A, Map<B, List<T>>>> {
        return groupingBy(f1, groupingBy(f2))
    }

    private fun getCaloricLevel(dish: Dish): Dish.CaloricLevel {
        return when {
            dish.calories <= 400 -> Dish.CaloricLevel.DIET
            dish.calories <= 700 -> Dish.CaloricLevel.NORMAL
            else -> Dish.CaloricLevel.FAT
        }
    }

    class GroupingBuilder<T, D, K>(private val collector: Collector<in T, *, Map<K, D>>) {

        companion object {
            fun <T, K> groupOn(classifier: Function<in T, out K>): GroupingBuilder<T, List<T>, K> {
                return GroupingBuilder(groupingBy(classifier))
            }

            fun <T, K> groupOn(classifier: (T) -> K): GroupingBuilder<T, List<T>, K> {
                return GroupingBuilder(groupingBy(classifier))
            }
        }

        fun <J> after(classifier: Function<in T, out J>): GroupingBuilder<T, Map<K, D>, J> {
            return GroupingBuilder(groupingBy(classifier, collector))
        }

        fun <J> after(classifier: (T) -> J): GroupingBuilder<T, Map<K, D>, J> {
            return GroupingBuilder(groupingBy(classifier, collector))
        }

        fun get(): Collector<in T, *, Map<K, D>> = collector
    }
}