package chap06

import Dish
import dishTags
import menu
import java.util.*
import java.util.Comparator.comparingInt
import java.util.stream.Collectors.*

object CollectorsSample {
    @JvmStatic
    fun main(args: Array<String>) {
        // toList
        menu.stream().collect(toList()).forEach(::println)
        // toSet
        menu.stream().collect(toSet()).forEach(::println)
        // toCollection
        menu.stream().collect(toCollection { hashSetOf<Dish>() }).forEach(::println)
        // counting
        println(menu.stream().collect(counting()))
        // summingInt
        println(menu.stream().collect(summingInt(Dish::calories)))
        // averagingInt
        println(menu.stream().collect(averagingInt(Dish::calories)))
        // summarizingInt
        println(menu.stream().collect(summarizingInt(Dish::calories)))
        // joining
        println(menu.stream().map(Dish::name).collect(joining(", ")))
        // maxBy
        println(menu.stream().collect(maxBy(comparingInt(Dish::calories))))
        // minBy
        println(menu.stream().collect(collectingAndThen(minBy(comparingInt(Dish::calories)), Optional<Dish>::get)))
        // reducing
        println(menu.stream().collect(reducing(0, Dish::calories, Int::plus)))
        // collectingAndThen
        println(menu.stream().collect(collectingAndThen(toList(), List<Dish>::size)))
        // groupingBy
        println(menu.stream().collect(groupingBy(Dish::type,
            flatMapping({ dish: Dish -> dishTags[dish.name]!!.stream() }, toSet<String>()))))
        // partitioningBy
        println(menu.stream().collect(partitioningBy(Dish::vegetarian,
            groupingBy(Dish::type))))
    }
}