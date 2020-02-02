package chap16

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors.toList
import kotlin.random.Random


const val DELAY_TIME = 1000L
private val RANDOM = Random(0)
private val formatter: DecimalFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.US))

fun delay() {
    try {
        Thread.sleep(DELAY_TIME)
    } catch (e: InterruptedException) {
        throw RuntimeException(e)
    }
}

fun randomDelay() {
    val delayTime = 500L + RANDOM.nextInt(2000)  // 0.5 ~ 2.5 sec
    try {
        Thread.sleep(delayTime)
    } catch (e: InterruptedException) {
        throw RuntimeException(e)
    }
}

fun format(number: Double): Double {
    synchronized(formatter) {
        return formatter.format(number).toDouble()
    }
}

fun <T> sequence(futures: List<CompletableFuture<T>>): CompletableFuture<List<T>> {
//    val allDoneFuture = CompletableFuture.allOf(*futures.toTypedArray())
//    return allDoneFuture.thenApply { futures.stream()
//        .map { future -> future.join() }
//        .collect(toList())
//    }

    return CompletableFuture.supplyAsync {
        futures.stream()
            .map { future -> future.join() }
            .collect(toList())
    }
}

fun Double.formatPrice() = String.format("%.2f", this)