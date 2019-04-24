package chap09

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable

// Observable의 작동 방식
fun main() {
    // Any 타입의 옵저버 인스턴스 선언
    val observer = object: Observer<Any> {
        // 에러 없이 모든 아이템의 방출이 완료될때 호출
        override fun onComplete() {
            println("onComplete() 호출됨")
        }
        // Observer가 구독을 시작하면 호출
        override fun onSubscribe(d: Disposable) {
            println("Observer가 구독을 시작함 disposable : $d")
        }

        // Observable이 onNext가 호출될 때 마다 아이템 방출
        override fun onNext(t: Any) {
            println("onNext() -> next Item : $t")
        }

        // Observable이 에러를 만났을 때 호출
        override fun onError(e: Throwable) {
            println("onError() -> ${e.message}")
        }
    }

    // 리스트를 Observable로 생성
    val observable = listOf(1, "Two", 3, "Four", 5.6f).toObservable()

    // Observer 값으로 Observable 을 구독
    observable.subscribe(observer)

    // 아이템이 리스트인 Observable 생성
    val observableOnList = Observable.just(listOf(1, "Two", 3, "Four", 5.6f), listOf("Item1"),
        listOf(1, 2, 3))
    observableOnList.subscribe(observer)
}