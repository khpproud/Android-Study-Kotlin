package chap17

import java.util.concurrent.Flow.*

class TempProcessor : Processor<TempInfo, TempInfo> {

    private lateinit var subscriber: Subscriber<in TempInfo>

    override fun subscribe(subscriber: Subscriber<in TempInfo>) {
        this.subscriber = subscriber
    }

    override fun onSubscribe(subscription: Subscription) {
        subscriber.onSubscribe(subscription)
    }

    override fun onNext(item: TempInfo) {
        subscriber.onNext(TempInfo(item.town, (item.temp - 32) * 5 / 9))
    }

    override fun onError(throwable: Throwable?) {
        subscriber.onError(throwable)
    }

    override fun onComplete() {
        subscriber.onComplete()
    }
}