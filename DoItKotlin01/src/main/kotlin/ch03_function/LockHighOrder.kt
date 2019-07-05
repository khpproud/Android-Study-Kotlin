package ch03_function

import java.util.concurrent.locks.ReentrantLock

var sharable = 1                // 보호가 필요한 공유 자원

fun main() {
    val reLock = ReentrantLock()

    lock(reLock, { criiticalFunc() })
    lock(reLock) { criiticalFunc() }
    lock(reLock, ::criiticalFunc)

    println(sharable)
}

fun criiticalFunc() {
    sharable += 1                       // 공유 자원 접근
}

fun <T> lock(reLock: ReentrantLock, body: () -> T): T {
    reLock.lock()                       // 잠금
    try {
        return body()                   // 임계 영역 코드 실행
    } finally {
        reLock.unlock()                 // 잠금 해제
    }
}