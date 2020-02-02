package chap01

import java.util.function.Predicate
import java.util.stream.Collectors.toList
import kotlin.reflect.KFunction1
import kotlin.streams.toList

object FilteringApples {
    @JvmStatic
    fun main(args: Array<String>) {
        val inventory = listOf(
            Apple(80, "green"),
            Apple(155, "green"),
            Apple(120, "red"))

        println("GreenApples: " + filterGreenApples(inventory))
        println("HeavyApples: " + filterHeavyApples(inventory))

        /**
         * In java, below method's expression is available:
         *  filterApples(inventory, FilteringApples::isGreenApple)
         * but in kotlin, available with
         *  filterApples(inventory, Predicate(::isGreenApple))
         *  or
         *  filterApples(inventory, Predicate { apple -> isGreenApple(apple) })
         */
        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        val greenApples = filterApples(inventory, Predicate(::isGreenApple))

        /**
         * or (Apple) -> Boolean with lambda function(this is equivalent with below)
         * or KFunction1<Apple, Boolean> with Kotlin reflect library KFunction1
         */
        val greenApples2 = filterApples(inventory, ::isGreenApple)
        println(greenApples2)

        // Heavy apples
        val heavyApples = filterApples(inventory, Predicate(::isHeavyApple))
        println(heavyApples)

        // lambda expression
        val heavyApples2 = filterApples(inventory) {
            it.weight > 100
        }
        println(heavyApples2)

        val weirdApples = filterApples(inventory) {
            it.weight < 80 || "brown" == it.color
        }
        println(weirdApples)

        // with stream
        val greenApples3 = inventory.stream().filter(::isGreenApple).collect(toList())
        println(greenApples3)

        val heavyApples3 = inventory.parallelStream()
            .filter { apple -> apple.weight > 100 && "red" == apple.color}.toList()
        println(heavyApples3)
    }

    // Before Java8
    fun filterGreenApples(inventory: List<Apple>): List<Apple> {
        val result = arrayListOf<Apple>()
        for (apple in inventory) {
            if ("green" == apple.color) {
                result.add(apple)
            }
        }
        return result
    }

    fun filterHeavyApples(inventory: List<Apple>): List<Apple> {
        val result = arrayListOf<Apple>()
        for (apple in inventory) {
            if (apple.weight > 150) {
                result.add(apple)
            }
        }
        return result
    }

    fun isGreenApple(apple: Apple): Boolean = "green" == apple.color

    fun isHeavyApple(apple: Apple): Boolean = apple.weight > 150

    // Normalized With Java8
    fun filterApples(inventory: List<Apple>, p: Predicate<Apple>): List<Apple> {
        val result = arrayListOf<Apple>()
        for (apple in inventory) {
            if (p.test(apple)) {
                result.add(apple)
            }
        }
        return result
    }

    // Kotlin lambda function style expression
    fun filterApples(inventory: List<Apple>, p: (Apple) -> Boolean): List<Apple> {
        val result = arrayListOf<Apple>()
        for (apple in inventory) {
            if (p(apple)) {
                result.add(apple)
            }
        }
        return result
    }

    // KFunction1<Apple, Boolean> : By reflect library
//    fun filterApples(inventory: List<Apple>, p: KFunction1<Apple, Boolean>): List<Apple> {
//        val result = arrayListOf<Apple>()
//        for (apple in inventory) {
//            if (p.call(apple)) {
//                result.add(apple)
//            }
//        }
//        return result
//    }
}

data class Apple(
    val weight: Int,
    val color: String = "") {
    override fun toString(): String {
        return String.format("Apple{color='%s', weight=%d}", color, weight)
    }
}