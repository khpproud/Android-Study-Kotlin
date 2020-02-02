package chap17

import java.util.concurrent.Executors
import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.Flow.Subscription

class TempSubscription(private val subscriber: Subscriber<in TempInfo>,
                       private val town: String) : Subscription {

    private val executor = Executors.newSingleThreadExecutor()

    override fun request(n: Long) {
        executor.submit {
            for (i in 0 until n) {
                try {
                    subscriber.onNext(TempInfo.fetch(town))
                } catch (e: Exception) {
                    subscriber.onError(e)
                    break
                }
            }
        }
    }

    override fun cancel() {
        subscriber.onComplete()
    }
}