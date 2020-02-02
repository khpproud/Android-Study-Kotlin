package chap02

object FilteringApples {
    @JvmStatic
    fun main(args: Array<String>) {
        val inventory = listOf(
            Apple(80, Color.GREEN),
            Apple(155, Color.GREEN),
            Apple(120, Color.RED)
        )

        // Anonymous function
        val greenApples = filter(inventory, object : ApplePredicate {
            override fun test(a: Apple): Boolean {
                return a.color == Color.GREEN
            }
        })
        println(greenApples)

        // Behavior Parameterization
        val heavyApples = filter(inventory, AppleWeightPredicate())
        println(heavyApples)

        val redAndHeavyApples = filter(inventory, AppleRedAndHeavyPredicate())
        println(redAndHeavyApples)

        val greenAndLightApples = filter(inventory) { apple ->
            apple.weight < 150 && apple.color == Color.GREEN
        }
        println(greenAndLightApples)
    }

    fun filter(inventory: List<Apple>, p: ApplePredicate): List<Apple> {
        val result = arrayListOf<Apple>()
        for (apple in inventory) {
            if (p.test(apple)) {
                result.add(apple)
            }
        }
        return result
    }

    fun filter(inventory: List<Apple>, p: (Apple) -> Boolean): List<Apple> {
        val result = arrayListOf<Apple>()
        for (apple in inventory) {
            if (p(apple)) {
                result.add(apple)
            }
        }
        return result
    }
}

data class Apple(val weight: Int, val color: Color)

enum class Color {
    RED, GREEN
}

interface ApplePredicate {
    fun test(a: Apple): Boolean
}

class AppleWeightPredicate : ApplePredicate {
    override fun test(a: Apple): Boolean = a.weight > 150
}

class AppleColorPredicate : ApplePredicate {
    override fun test(a: Apple) = a.color == Color.GREEN
}

class AppleRedAndHeavyPredicate : ApplePredicate {
    override fun test(a: Apple) = a.color == Color.RED && a.weight > 150
}