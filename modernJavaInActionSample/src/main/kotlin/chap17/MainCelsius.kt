package chap17

import java.util.concurrent.Flow.Publisher

fun main() {
    getCelsiusTemperature("New York").subscribe(TempSubscriber())
}

private fun getCelsiusTemperature(town: String): Publisher<TempInfo> {
    return Publisher { subscriber ->
        val processor = TempProcessor()
        processor.subscribe(subscriber)
        processor.onSubscribe(TempSubscription(processor, town))
    }
}