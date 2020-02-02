package chap05

import Dish
import menu
import java.util.*
import java.util.stream.Collectors.toList

object Mapping {
    @JvmStatic
    fun main(args: Array<String>) {
        // Map
        val dishNames = menu.stream().map(Dish::name).collect(toList())
        println(dishNames)

        val words = listOf("Java8", "Lambdas", "In", "Action")
        val wordLengths = words.stream()
            .map(String::length)
            .collect(toList())
        println(wordLengths)
        println("-----")

        // FlatMap
        // problem - List<String[]>
        val helloWords = listOf("Hello", "World")
        val uniqueWords = helloWords.stream()
            .map { word -> word.split("").filter { it.isNotEmpty() } }
            .distinct()
            .collect(toList())
        println(uniqueWords)
        println("-----")

        // With Arrays::stream
//        val uniqueWords2 = helloWords.stream()
//            .map { word -> word.split("").filter { it.isNotEmpty() }.toTypedArray() }
//            .flatMap(Arrays::stream)
//            .distinct()
//            .collect(toList())

        // Or
//        val uniqueWords2 = helloWords.stream()
//            // Stream<List<String>>
//            .map { word -> word.split("").filter { it.isNotEmpty() } }
//            // Flatten: Stream<String>
//            .flatMap { t: List<String> -> t.stream() }
//            .distinct()
//            .collect(toList())

        // Or
        val uniqueWords2 = helloWords.stream()
            .flatMap { word ->
                Arrays.stream(word.split("").filter { it.isNotEmpty() }.toTypedArray()) }
            .distinct()
            .collect(toList())

        println(uniqueWords2)
        println("-----")

        // Number Pair
        val numbers1 = listOf(1, 2, 3, 4, 5)
        val numbers2 = listOf(6, 7, 8)
        val pairs =
            numbers1.stream()
                .flatMap { i -> numbers2.stream()
                    .filter { j -> (i + j) % 3 == 0 }
                    .map { j -> intArrayOf(i, j) }
                }
                .collect(toList())
        pairs.forEach { pair -> println("[${pair[0]}, ${pair[1]}]")}
    }
}