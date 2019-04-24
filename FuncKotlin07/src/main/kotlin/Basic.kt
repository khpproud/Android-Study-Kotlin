package chap07

import kotlin.concurrent.thread

// thread 사용 하는 간단한 예제 : 다른 스레드가 완료되는 동안 메인스레드가 기다려야 함(임의의 시간을 설정해 기다리므로 이쁘지 않음)
//fun main() {
//    thread {
//        Thread.sleep(1000)
//        println("Hello by ${Thread.currentThread().name}")
//    }
//    println("Hello by ${Thread.currentThread().name}")
//    Thread.sleep(2000)
//}

// thread 사용 하는 간단한 예제 computation 이라는 스레드 참조에 join()을 호출해 완료되기 까지 기다림
fun main() {
    val computation = thread {
        Thread.sleep(1000)
        println("Hello by ${Thread.currentThread().name}")
    }
    println("Hello by ${Thread.currentThread().name}")
    computation.join()
}