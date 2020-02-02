package chap03

object FunctionalInterfaceSample {
    @JvmStatic
    fun main(args: Array<String>) {

    }

    enum class Color { RED, GREEN, YELLOW }

    data class Apple(val weight: Int, val color: Color)
}