package chap03

import java.util.Comparator.comparing
import java.util.function.Predicate

object Sorting {
    @JvmStatic
    fun main(args: Array<String>) {
        val inventory = listOf(
            Apple(155, Color.GREEN),
            Apple(80),
            Apple(55, Color.RED),
            Apple(90, Color.BLUE),
            Apple(130, Color.YELLOW)
        )

        // init AppleComparator
        println(inventory.sortedWith(AppleComparator()))
        
        // Anonymous Class
        val inventory2 = inventory.sortedWith(object : Comparator<Apple> {
            override fun compare(o1: Apple, o2: Apple): Int {
                return o1.weight.compareTo(o2.weight)
            }
        })

        println(inventory2)

        // Lambda expression
        val inventory3 = inventory.sortedWith(Comparator { o1, o2 ->
            o1.weight.compareTo(o2.weight)
        })
        println(inventory3)

        // Method Reference
        val inventory4 = inventory.sortedWith(comparing(Apple::weight))
        println(inventory4)

        // Constructor Reference
        val c1: (Int) -> Apple = ::Apple
        // With KFunction2 type
        val c2: (Int, Color) -> Apple = ::Apple
        println(c2.invoke(120, Color.RED))

        val weights = arrayListOf(7, 3, 4, 10)
        val apples = map(weights, ::Apple)

        // Combination of operator
        val reversed = inventory.sortedWith(comparing(Apple::weight).reversed())
        println(reversed)

        val linked = inventory.sortedWith(comparing(Apple::weight)
            .thenComparing(Apple::color))
        println(linked)

        val greenApples: Predicate<Apple> = Predicate { apple -> apple.color == Color.GREEN }
        val notGreenApples = greenApples.negate()
        println(filter(inventory, notGreenApples))

        val notGreenAndLightApple = notGreenApples.and { apple -> apple.weight < 100 }
        println(filter(inventory, notGreenAndLightApple))
    }

    private fun <T> filter(list: List<T>, p: Predicate<T>): List<T> {
        val result = arrayListOf<T>()
        for (t in list) {
            if (p.test(t)) {
                result.add(t)
            }
        }
        return result
    }

    private fun <T> map(list: List<Int>, f: (Int) -> T): List<T> {
        val result = arrayListOf<T>()
        for (i in list) {
            result.add(f.invoke(i))
        }
        return result
    }

    enum class Color { RED, GREEN, BLUE, YELLOW }

    data class Apple @JvmOverloads constructor(val weight: Int = 0, val color: Color) {
        constructor(weight: Int): this(weight, color = Color.GREEN)
    }

    class AppleComparator : Comparator<Apple> {
        override fun compare(o1: Apple, o2: Apple): Int {
            return o1.weight.compareTo(o2.weight)
        }
    }
}