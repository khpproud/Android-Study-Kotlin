package chap17.rxJava

fun main() {
    val observable = TempObservable.getTemperature("New York")
//    observable.subscribe(TempObserver())
    observable.blockingSubscribe(TempObserver())

//    try {
//        Thread.sleep(10_000L)
//    } catch (e: InterruptedException) {
//        throw RuntimeException(e)
//    }
}