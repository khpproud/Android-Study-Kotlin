package chap15

import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future

object Functions {
    @JvmStatic
    fun main(args: Array<String>) {
        val x = 5
        sequential(5)

        try {
            futureBased(x)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }
}

fun f(x: Int) = x * 2

fun g(y: Int) = y + 1

fun fo(x: Int): Int = Integer.valueOf(x * 2)

fun go(y: Int): Int = Integer.valueOf(y + 1)

fun ff(x: Int): Future<Int> = CompletableFuture<Int>().completeAsync { x * 2 }

fun gf(x: Int): Future<Int> = CompletableFuture<Int>().completeAsync { Integer.valueOf(x + 1) }

fun sequential(x: Int) {
    val y = f(x)
    val z = g(x)
    println(y + z)
}

@Throws(InterruptedException::class, ExecutionException::class)
fun futureBased(x: Int) {
    val y: Future<Int> = ff(x)
    val z: Future<Int> = gf(x)
    println(y.get() + z.get())
}