package chap17

import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.Flow.Subscription

class TempSubscriber : Subscriber<TempInfo> {

    private lateinit var subscription: Subscription

    override fun onSubscribe(subscription: Subscription) {
        this.subscription = subscription
        subscription.request(1)
    }

    override fun onNext(item: TempInfo) {
        println(item)
        subscription.request(1)
    }

    override fun onError(throwable: Throwable) {
        println(throwable.message)
    }

    override fun onComplete() {
        println("Done!")
    }
}