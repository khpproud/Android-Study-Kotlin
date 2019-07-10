package ch11_coroutine

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// 상호 배제(Mutual Exclusion) 사용 방법
val mutex = Mutex()                     // Mutex 인스턴스 생성
var counter2 = 0

fun main() = runBlocking {
    massiveRun {
        mutex.withLock {
            counter2++                          // 임계 구역 코드
        }
    }
    println("Counter = $counter2")
}