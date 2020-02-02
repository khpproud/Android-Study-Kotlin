package chap17

import java.util.concurrent.Flow.Publisher

fun main() {
    getTemperatures("New York").subscribe(TempSubscriber())
}

private fun getTemperatures(town: String): Publisher<TempInfo> {
    return Publisher { subscriber -> subscriber.onSubscribe(TempSubscription(subscriber, town)) }
}