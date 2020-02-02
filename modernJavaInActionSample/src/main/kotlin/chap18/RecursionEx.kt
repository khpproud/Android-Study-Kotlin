package chap18

import java.util.stream.LongStream

fun main() {
    val n = 20
    println(factorialIterative(n))
    println(factorialRecursive(n))
    println(factorialStreams(n))
    println(factorialTailRecursive(n))
}

fun factorialIterative(n: Int): Long {
    var r = 1L
    for (i in 1.. n) {
        r *= i
    }
    return r
}

fun factorialRecursive(n: Int): Long {
    return if (n == 1) 1L else n * factorialRecursive(n - 1)
}

private fun factorialStreams(n: Int): Long {
    return LongStream.rangeClosed(1, n.toLong()).reduce(1) { a, b -> a * b }
}

private fun factorialTailRecursive(n: Int): Long {
    return factorialHelper(1, n.toLong())
}

private tailrec fun factorialHelper(acc: Long, n: Long): Long {
    return if (n == 1L) acc else factorialHelper(acc * n, n - 1)
}

//private inline fun measurePerf(f: () -> Long) {
//    val start = System.nanoTime()
//    println(f.invoke())
//    val duration = (System.nanoTime() - start) / 1_000_000
//    println("function executed: $duration ms")
//}