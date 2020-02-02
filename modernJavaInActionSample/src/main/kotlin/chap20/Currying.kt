package chap20

import java.util.stream.Stream

fun main() {
    val r = multiply(2, 10)
    println(r)

    Stream.of(1, 3, 5, 7)
        .map { multiplyCurry(2).invoke(it) }
        .forEach(::println)
}

private fun multiply(x: Int, y: Int) = x * y

private fun multiplyCurry(x: Int) = { y: Int -> x * y }