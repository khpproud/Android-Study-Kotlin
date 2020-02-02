package chap17.rxJava

import chap17.TempInfo
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class TempObserver : Observer<TempInfo> {
    override fun onComplete() {
        println("Done!")
    }

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(t: TempInfo) {
        println(t)
    }

    override fun onError(e: Throwable) {
        println("Got problem ${e.message}")
    }
}