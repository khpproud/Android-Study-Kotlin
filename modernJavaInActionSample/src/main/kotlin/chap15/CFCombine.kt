package chap15

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

fun main() {
    val executorService = Executors.newFixedThreadPool(10)
    val x = 1337

    val a = CompletableFuture<Int>()
    val b = CompletableFuture<Int>()
    val c = a.thenCombine(b) { y, z -> y + z }
    executorService.submit { a.complete(f(x)) }
    executorService.submit { b.complete(g(x)) }

    println(c.get())
    executorService.shutdown()
}