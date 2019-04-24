package chap09

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

// RxKotlin 푸시 메커니즘과 풀 메커니즘 비교
fun main() {

    var list = listOf(1, "Two", 3, "hi", 5.6f)

    // 일반적인 리스트를 이용한 풀 메커니즘
    val iterator = list.iterator()
    while (iterator.hasNext())
        println(iterator.next())

    // 생산자가 소비자에게 값을 알림으로 푸시
    // Observable(구독할 수 있는) 인스턴스가 리스트에 의해 생성
    var observable = list.toObservable()

    // 옵저버를 구독
    observable.subscribeBy(
        // 람다 구독자에 대한 명명된 인자
        onNext = { println(it) },
        onComplete = { println("Complete!") },
        onError = { it.printStackTrace() }
    )
}