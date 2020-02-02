package chap19

import java.util.stream.Collectors.joining
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.math.sqrt

fun main() {
    println(primes(25).map(Int::toString).collect(joining(", ")))
}

private fun primes(n: Int): Stream<Int> {
    return Stream.iterate(2, Int::inc)
        .filter(::isPrime)
        .limit(n.toLong())
}

private fun isPrime(candidate: Int): Boolean {
    val candidateRoot = sqrt(candidate.toDouble()).toInt()
    return IntStream.rangeClosed(2, candidateRoot)
        .noneMatch { i -> candidate % i == 0 }
}