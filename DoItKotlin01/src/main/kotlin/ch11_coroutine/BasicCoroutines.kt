package ch11_coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 코루틴 기본 사용 예
fun main() {                                    // 메인스레드의 문맥
    GlobalScope.launch {                        // 새로운 코루틴을 백그라운드에 실행
        delay(1000L)                    // 1초의 non blocking 지연(기본 단위는 ms)
        println("world!")                       // 지연 후 출력
        doSomething()
    }
    println("Hello, ")                          // 메인스레드의 코루틴이 지연되는 동안 계속 실행
    Thread.sleep(2000L)                 // 메인스레드가 JVM에서 바로 종료되지 않게 2초 기다림
}

suspend fun doSomething() = println("Do Something!")