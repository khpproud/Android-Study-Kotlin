package chap16.v1

import chap16.ExchangeService
import chap16.ExchangeService.Money
import chap16.v1.BestPriceFinder.findPricesFuture
import chap16.v1.BestPriceFinder.findPricesInUSD2
import chap16.v1.BestPriceFinder.findPricesInUSD3
import chap16.v1.BestPriceFinder.findPricesInUSDJava7
import chap16.v1.BestPriceFinder.findPricesParallel
import chap16.v1.BestPriceFinder.findPricesSequential
import chap16.v1.BestPriceFinder.findPricesUSD
import java.util.concurrent.*
import java.util.function.Supplier
import java.util.stream.Collectors.toList
import java.util.stream.Stream

fun main() {
    val product = "Galaxy27S"
    execute("sequential") { findPricesSequential(product) }
    execute("parallel") { findPricesParallel(product) }
    execute("composed CompletableFuture") { findPricesFuture(product) }
    execute("combined USD CompletableFuture") { findPricesUSD(product) }
    execute("combined USD CompletableFuture Java7") { findPricesInUSDJava7(product) }
    execute("combined USD CompletableFuture v2") { findPricesInUSD2(product) }
    execute("combined USD CompletableFuture v3") { findPricesInUSD3(product) }
}

object BestPriceFinder {
    val shops = listOf(
        Shop("BestPrice"),
        Shop("LetsSaveBig"),
        Shop("MyFavoriteShop"),
        Shop("BuyItAll")
//        , Shop("DiscountShop")
    )

    private val executor = Executors.newFixedThreadPool(shops.size) {
        Thread(it).apply { isDaemon = true }
    }

    fun findPricesSequential(product: String): List<String> {
        return shops.stream()
            .map { shop -> shop.getPriceInfo(product) }
            .collect(toList())
    }

    fun findPricesParallel(product: String): List<String> {
        return shops.parallelStream()
            .map { shop -> shop.getPriceInfo(product) }
            .collect(toList())
    }

    fun findPricesFuture(product: String): List<String> {
        val priceFuture = shops.stream()
            .map { shop -> CompletableFuture.supplyAsync<String>(
                Supplier { shop.getPriceInfo(product) }, executor) }
            .collect(toList())

        return priceFuture.stream()
            .map(CompletableFuture<String>::join)
            .collect(toList())
    }

    fun findPricesUSD(product: String): List<String> {
        val priceFutures: ArrayList<CompletableFuture<Double>> = arrayListOf()
        for (shop in shops) {
            val futurePriceInUSD = CompletableFuture.supplyAsync { shop.getPrice(product) }
                .thenCombine(
                    CompletableFuture.supplyAsync {
                        ExchangeService.getRate(Money.EUR, Money.USD)
                    }
                         // Java9
                        .completeOnTimeout(ExchangeService.DEFAULT_RATE, 1, TimeUnit.SECONDS)
                ) { price, rate -> price * rate }
                // Java9
                .orTimeout(3, TimeUnit.SECONDS)
            priceFutures.add(futurePriceInUSD)
        }
        // Since the shop is not accessed out of for loop,
        // so the getName() call below has been commented out.
        return priceFutures.stream()
            .map(CompletableFuture<Double>::join)
            .map { price -> /* shop.name +*/"price is $price" }
            .collect(toList())
    }

    fun findPricesInUSDJava7(product: String): List<String> {
        val executor = Executors.newCachedThreadPool()
        val priceFutures: ArrayList<Future<Double>> = arrayListOf()
        for (shop in shops) {
            val futureRate = executor.submit(object : Callable<Double> {
                override fun call(): Double {
                    return ExchangeService.getRate(Money.EUR, Money.USD)
                }
            })
            val futurePriceInUSD = executor.submit(object : Callable<Double> {
                override fun call(): Double {
                    try {
                        val priceInEUR = shop.getPrice(product)
                        return priceInEUR * futureRate.get()
                    } catch (e : Exception) {
                        throw RuntimeException(e)
                    }
                }
            })
            priceFutures.add(futurePriceInUSD)
        }
        val prices: ArrayList<String> = arrayListOf()
        for (priceFuture in priceFutures) {
            try {
                prices.add("price is ${priceFuture.get()}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return prices
    }

    fun findPricesInUSD2(product: String): List<String> {
        val priceFutures: ArrayList<CompletableFuture<String>> = arrayListOf()
        for (shop in shops) {
            val futurePricesInUSD = CompletableFuture.supplyAsync {
                shop.getPrice(product)
            }.thenCombine(
                CompletableFuture.supplyAsync {
                    ExchangeService.getRate(Money.EUR, Money.USD)
                }
            ) { price, rate -> price * rate }
                .thenApply { price -> shop.name + " price is " + price }
            priceFutures.add(futurePricesInUSD)
        }
        return priceFutures.stream()
            .map(CompletableFuture<String>::join)
            .collect(toList())
    }

    fun findPricesInUSD3(product: String): List<String> {
        val priceFuturesStream: Stream<CompletableFuture<String>> = shops.stream()
            .map { shop -> CompletableFuture.supplyAsync { shop.getPrice(product) }
                .thenCombine(
                    CompletableFuture.supplyAsync { ExchangeService.getRate(Money.EUR, Money.USD) }
                ) { price, rate -> price * rate }
                .thenApply { price -> shop.name + " price is " + price }
            }
        val priceFutures: List<CompletableFuture<String>> = priceFuturesStream.collect(toList())
        return priceFutures.stream()
            .map(CompletableFuture<String>::join)
            .collect(toList())
    }
}

private inline fun execute(msg: String, supplier: () -> List<String>) {
    val start = System.nanoTime()
    println(supplier())
    val duration = ((System.nanoTime() - start) / 1_000_000) // msecs
    println("$msg done in $duration msecs")
}

