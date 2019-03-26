package five.three


// 함수를 작업으로 제공
fun <T> filter(list: List<T>, predicate: (T) -> Boolean) {
    var visibleTasks = emptyList<T>()
    for (elem in list) {
        if (predicate(elem))
            visibleTasks += elem
    }
}

// 옵저버(리스너) 패턴에 사용 예
var listeners: List<() -> Unit> = emptyList()           // 모든 리스너를 저장할 빈 리스트르 만듬
fun addListener(listener: () -> Unit) {                 // 리스트에 리스너를 추가
    listeners += listener
}

fun invokeListeners() {
    for (listener in listeners)
        listener()                                      // 모든 리스너를 순회하면서 호출
}

// 스레드 작업 후 콜백 사용 예
fun longOperationAsync(longOperation: () -> Unit, callback: () -> Unit) {
    Thread {
        longOperation()                                 // 오래걸리는 작업
        callback()                                      // 인수로 제공된 콜백 실행
    }.start()
}

fun main() {
    //var visibleTasks = filter(tasks) { it.active }
    // 오래 걸리는 작업 스레드 호출
    longOperationAsync(longOperation = { Thread.sleep(1000L)},
        callback = { println("After second!!!")})
    // 즉시 실행할 메소드
    println("Now!!!")
}