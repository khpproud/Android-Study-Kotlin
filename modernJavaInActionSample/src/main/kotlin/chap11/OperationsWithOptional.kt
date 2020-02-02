package chap11

import java.util.*
import kotlin.math.max

fun main() {
    println(maxOf(Optional.of(3), Optional.of(5)))
    println(maxOf(Optional.empty(), Optional.of(5)))

    val opt1 = Optional.of(5)
    val opt2 = opt1.or { Optional.of(4) }
    val opt3 = Optional.empty<Int>().or { Optional.of(4) }

    println(Optional.of(5).or { Optional.of(4) })
    println(opt2.get())
    println(opt3.get())
}

fun maxOf(i: Optional<Int>, j: Optional<Int>): Optional<Int> {
    return i.flatMap { a: Int -> j.map { b: Int -> max(a, b) } }
}