package chap05

import java.util.stream.Collectors.toList

object Laziness {
    @JvmStatic
    fun main(args: Array<String>) {
        val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        val twoEvenSquares = numbers.stream()
            .filter { n ->
                println("filtering $n")
                n % 2 == 0
            }
            .map { n ->
                println("mapping $n")
                n * n
            }
            .limit(3)
            .collect(toList())
    }
}