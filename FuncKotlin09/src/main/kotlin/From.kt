package chap09

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

// Observable.from~ 메소드 사용 예
fun main() {
    val observer = object: Observer<String> {
        override fun onComplete() {
            println("onComplete()")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe()")
        }

        override fun onNext(t: String) {
            println("onNext() -> $t")
        }

        override fun onError(e: Throwable) {
            println("onError() -> ${e.message}")
        }
    }

    // iterables
    val list = listOf("Item1", "Item2", "Item3", "Item4")
    val observableFromIterable = Observable.fromIterable(list)
    observableFromIterable.subscribe(observer)

    // Callable
    val callable = Callable<String> { "From Callable" }
    val observableFromCallable = Observable.fromCallable(callable)
    observableFromCallable.subscribe(observer)

    // Future
    val future = object: Future<String> {
        val returnStr = "From Future"
        override fun get(timeout: Long, unit: TimeUnit?): String  = returnStr

        override fun isDone(): Boolean = true

        override fun cancel(mayInterruptIfRunning: Boolean): Boolean = false

        override fun isCancelled(): Boolean  = false

        override fun get(): String  = returnStr
    }
    val observableFromFuture = Observable.fromFuture(future)
    observableFromFuture.subscribe(observer)
}