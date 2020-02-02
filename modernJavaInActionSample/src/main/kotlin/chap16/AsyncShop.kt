package chap16

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import kotlin.random.Random

data class AsyncShop(val name: String) {
    private val random = Random(name[0].toInt() * name[1].toInt() * name[2].toInt())

    fun getPrice(product: String): Future<Double> {
//        val futurePrice = CompletableFuture<Double>()
//        Thread {
//            try {
//                val price = calculatePrice(product)
//                futurePrice.complete(price)
//            } catch (e: Exception) {
//                futurePrice.completeExceptionally(e)
//            }
//        }.start()
//        return futurePrice

        return CompletableFuture.supplyAsync { calculatePrice(product) }
    }

    private fun calculatePrice(product: String): Double {
        delay()
        if (true) {
            throw RuntimeException("product not available")
        }
        return format(random.nextDouble() * product[0].toInt() + product[1].toInt())
    }
}