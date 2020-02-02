package chap05

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.function.IntSupplier
import java.util.stream.IntStream
import java.util.stream.Stream

object BuildingStreams {
    @JvmStatic
    fun main(args: Array<String>) {
        // Stream.of
        val stream = Stream.of("Java 8", "Lambdas", "In", "Action")
        stream.map(String::toUpperCase).forEach(::println)

        // Stream.empty
        val emptyStream = Stream.empty<String>()
        println(emptyStream.count())

        // Arrays.stream
        val numbers: IntArray = intArrayOf(2, 3, 5, 7, 11, 13)
        println(Arrays.stream(numbers).sum())

        // Stream.iterate
        IntStream.iterate(0) { n -> n + 2}
            .limit(10)
            .forEach(::print)
        println("\n-------")

        // Fibonacci with iterate
        Stream.iterate(intArrayOf(0, 1)) { t -> intArrayOf(t[1], t[0] + t[1]) }
            .limit(10)
            .forEach { t -> println("(${t[0]}, ${t[1]})") }

        Stream.iterate(intArrayOf(0, 1)) { t -> intArrayOf(t[1], t[0] + t[1]) }
            .limit(10)
            .map { t -> t[0] }
            .forEach(::println)

        // Random stream of doubles with Stream.generate
        Stream.generate(Math::random)
            .limit(5)
            .forEach(::println)

        // Stream of 1s with Stream.generate
        IntStream.generate { 1 }
            .limit(5)
            .forEach(::print)
        println()

        // Do not use this code(Mutable state): just example)
        val fib: IntSupplier = object : IntSupplier {
            var previous: Int = 0
            var current: Int = 1

            override fun getAsInt(): Int {
                val nextValue = this.previous + this.current
                this.previous = this.current
                this.current = nextValue
                return this.previous
            }
        }
        IntStream.generate(fib).limit(10).forEach(::println)

        // File stream
        val uniqueWords: Long = Files.lines(
            Paths.get("src/main/resources/chap05/data.txt"), Charset.defaultCharset())
            .flatMap { line -> Arrays.stream(line.split(Regex("\\s")).toTypedArray()) }
            .distinct()
            .peek(::println)
            .count()

        println("There are $uniqueWords unique words in data.txt")

    }
}