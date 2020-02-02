package chap15

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

fun main() {
    val executorService = Executors.newFixedThreadPool(10)
    val x = 1337

    val a: CompletableFuture<Int> = CompletableFuture()
    executorService.submit { a.complete(f(x)) }
    val b = g(x)
    println(a.get() + b)

    executorService.shutdown()
}