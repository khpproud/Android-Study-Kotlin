package chap17.rxJava

fun main() {
    val observable = TempObservable.getCelsiusTemperatures("New York", "Chicago", "LA")
    observable.blockingSubscribe(TempObserver())

    val negObservable = TempObservable.getNegativeTemperature("New York")
    negObservable.blockingSubscribe(TempObserver())
}