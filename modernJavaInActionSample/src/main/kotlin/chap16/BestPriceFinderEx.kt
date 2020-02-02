package chap16

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.function.Supplier
import java.util.stream.Collectors.toList
import java.util.stream.Stream

private val shops: List<Shop> = listOf(
    Shop("BestPrice"),
    Shop("LetsSaveBig"),
    Shop("MyFavoriteShop"),
    Shop("BuyItAll"),
    Shop("MyPrecious")
)

private val executor = Executors.newFixedThreadPool(shops.size) {
    Thread(it).apply { isDaemon = true }
}

fun main() {
    val product = "iPhoneZ"
//    execute("sequential") { findPricesSequential(product) }
//    execute("parallel") { findPricesParallel(product) }
//    execute("composed CompletableFuture") { findPricesFuture(product) }
    printPricesStream(product)
}

private fun findPricesSequential(product: String): List<String> {
    return shops.stream()
        .map { shop -> shop.getPrice(product) }
        .map { priceStr -> Quote.parse(priceStr) }
        .map(Discount::applyDiscount)
        .collect(toList())
}

private fun findPricesParallel(product: String): List<String> {
    return shops.parallelStream()
        .map { shop -> shop.getPrice(product) }
        .map { priceStr -> Quote.parse(priceStr) }
        .map(Discount::applyDiscount)
        .collect(toList())
}

private fun findPricesFuture(product: String): List<String> {
    val priceFutures: List<CompletableFuture<String>> = findPricesStream(product).collect(toList())

    return priceFutures.stream()
        .map(CompletableFuture<String>::join)
        .collect(toList())
}

private fun findPricesStream(product: String): Stream<CompletableFuture<String>> {
    return shops.stream()
        .map { shop -> CompletableFuture.supplyAsync(Supplier { shop.getPrice(product) }, executor) }
        .map { future -> future.thenApply { priceStr -> Quote.parse(priceStr) } }
        .map { future -> future.thenCompose {
                quote -> CompletableFuture.supplyAsync(Supplier { Discount.applyDiscount(quote) }, executor) }
        }
}

private fun printPricesStream(product: String) {
    val start = System.nanoTime()
    val futures: Array<CompletableFuture<String>> = findPricesStream(product)
        .map { f -> f.thenAccept { s -> println("$s (done in ${(System.nanoTime() - start) / 1_000_000} msecs)")} }
        .toArray { size -> arrayOfNulls<CompletableFuture<String>>(size) }
    CompletableFuture.allOf(*futures).join()
    println("All Shops have now responded in ${(System.nanoTime() - start) / 1_000_000} msecs")
}

private fun execute(msg: String, supplier: () -> List<String>) {
    val start = System.nanoTime()
    println(supplier())
    val duration = (System.nanoTime() - start) / 1_000_000
    println(msg + "done in " + duration + "msecs")
}
