package chap09

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

// 구독 및 페기(dispose) 예
fun main() {
    val observable = Observable.range(1, 5)

    // subscribe의 인자에 메소드 전달
    observable.subscribe( {
        // onNext()
        println("onNext() -> $it")
    }, {
        // onError()
        println("onError() -> ${it.message}")
    }, {
        // onComplete()
        println("onComplete()")
    })

    // subscribe의 인자에 observer 인스턴스 전달
    val observer = object: Observer<Int> {
        override fun onComplete() {
            println("onComplete()")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe()")
        }

        override fun onNext(t: Int) {
            println("onNext() -> $t")
        }

        override fun onError(e: Throwable) {
            println("onError() -> ${e.message}")
        }
    }
    observable.subscribe(observer)

    // 구독을 폐기
    val observableInterval = Observable.interval(100, TimeUnit.MILLISECONDS)
    val observerDisposable = object: Observer<Long> {
        lateinit var disposable: Disposable

        override fun onComplete() {
            println("onComplete()")
        }

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(t: Long) {
            println("onNext() -> $t")
            if (t >= 10 && !disposable.isDisposed) {
                // 구독을 중지
                disposable.dispose()
                println("dispose()")
            }
        }

        override fun onError(e: Throwable) {
            println("onError() ->${e.message}")
        }
    }

    runBlocking {
        observableInterval.subscribe(observerDisposable)
        delay(1500)
    }
}