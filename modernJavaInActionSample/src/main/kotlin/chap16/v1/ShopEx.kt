package chap16.v1

import chap16.delay
import chap16.formatPrice
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import kotlin.random.Random

fun main() {
    val shop = Shop("BestShop")
    val start = System.nanoTime()
    val futurePrice = shop.getPriceAsync("my favorite product")
    val invocationTime = ((System.nanoTime() - start) / 1_000_000)  // ms
    println("Invocation returned after $invocationTime msecs")

    doSomethingElse()

    try {
        val price = futurePrice.get()
        println(String.format("Price is %.2f", price))
    } catch (e: ExecutionException) {
        throw RuntimeException(e)
    } catch (e: InterruptedException) {
        throw RuntimeException(e)
    }

    val retrievalTime = ((System.nanoTime() - start) / 1_000_000)
    println("Price is returned after $retrievalTime msecs")
}

private fun doSomethingElse() = println("Doing something else...")

data class Shop(val name: String) {
    private val random = Random(name[0].toInt() * name[1].toInt() * name[2].toInt())

    fun getPrice(product: String): Double {
        return calculatePrice(product)
    }

    private fun calculatePrice(product: String): Double {
        delay()
        return random.nextDouble() * product[0].toInt() + product[1].toInt()
    }

    fun getPriceAsync(product: String): Future<Double> {
        val futurePrice = CompletableFuture<Double>()
        Thread {
            try {
                val price = calculatePrice(product)
                futurePrice.complete(price)
            } catch (e: Exception) {
                futurePrice.completeExceptionally(e)
            }
        }.start()
        return futurePrice
    }

    fun getPriceInfo(product: String) = "$name price is ${getPrice(product).formatPrice()}"
}