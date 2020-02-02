package chap15

import java.util.concurrent.Flow
import java.util.concurrent.Flow.*
import java.util.function.Consumer

fun main() {
    val c1 = SimpleCell("C1")
    val c2 = SimpleCell("C2")
    val c3 = SimpleCell("C3")

    c1.subscribe(c3)

    c1.onNext(10)
    c2.onNext(20)
}

open class SimpleCell(private var name: String,
                      private val subscribers: MutableList<Subscriber<in Int>> = mutableListOf())
    : Publisher<Int>, Subscriber<Int> {

    var value: Int = 0

    override fun subscribe(subscriber: Subscriber<in Int>) {
        subscribers.add(subscriber)
        println("subscribe")
    }

    fun subscribe(onNext: (Int) -> Unit) {
        subscribers.add(object : Subscriber<Int> {
            override fun onComplete() {}

            override fun onSubscribe(subscription: Subscription) {}

            override fun onNext(item: Int) {
                onNext.invoke(item)
            }

            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
            }
        })
    }

    override fun onComplete() {
        println("onComplete")
    }

    override fun onSubscribe(subscription: Subscription) {
        println("onSubscribe")
    }

    override fun onNext(item: Int) {
        value = item
        println("$name:$value")
        notifyAllSubscribers()
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun notifyAllSubscribers() {
        subscribers.forEach { subscriber -> subscriber.onNext(value) }
    }
}