package chap09

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

// Observable 생성 팩토리 메서드 - create
fun main() {
    val observer: Observer<String> = object: Observer<String> {
        override fun onComplete() {
            println("onComplete() - 모든 데이터 방출 완료됨")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe() - 옵저버가 구독을 시작함")
        }

        override fun onNext(t: String) {
            println("onNext() - $t")
        }

        override fun onError(e: Throwable) {
            println("onError() - ${e.message}")
        }
    }

    // 관찰할 소스로 ObservableEmitter<T> 인터페이스의 인스턴스를 가짐
    val observable: Observable<String> = Observable.create {
        it.onNext("Emitted 1")
        it.onNext("Emitted 2")
        it.onNext("Emitted 3")
        it.onNext("Emitted 4")
        it.onComplete()
    }

    observable.subscribe(observer)

    val observable2: Observable<String> = Observable.create {
        it.onNext("Emitted 1")
        it.onNext("Emitted 2")
        it.onError(Exception("My Exception"))
    }

    observable2.subscribe(observer)
}