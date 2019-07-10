package ch11_coroutine

import kotlinx.coroutines.*

// 코루틴의 기본 동작 제어하기
//repeat() 함수를 사용한 반복 동작 하기
//fun main() = runBlocking {
//    // GlobalScope 로 생명주기를 한정 했기 때문에 메인스레드가 종료되면 더이상 진행되지 않음
//    GlobalScope.launch {                        // 만일 launch 만 사용하면 종료되지 않음
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//    delay(1300L)
//}

// 코루틴 작업 취소하기 - 1.3초뒤 작업을 취소하고 main() 함수가 종료
//fun main() = runBlocking {
//    val job = launch {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//    delay(1300L)
//    job.cancel()
//}

// finally 의 실행을 보장
//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//
//            }
//        } finally {
//            withContext(NonCancellable) {       // finally 의 완전한 실행을 보장
//                println("I'm running finally")
//                delay(1000L)
//                println("Non-cancellable")
//            }
//        }
//    }
//    delay(1300L)
//    job.cancelAndJoin()                             // 작업을 취소하고 완료될 때 까지 기다림
//    println("main : Quit!")
//}

// 실행 상태의 판단
//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = GlobalScope.launch {
//        var nextPrintTime = startTime
//        var i = 0
//        while (/*i < 5*/isActive) {                    // 조건을 계산에 의해 반복 - 조건을 만족할 때까지 종료되지 않음
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("I'm sleeping ${i++}  : $nextPrintTime ms...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

// 코루틴의 시간 만료
fun main() = runBlocking {
    try {
        withTimeout(1300L) {                // 1300ms 후 TimeOut 예외 발생
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)

            }
        }
    } catch (e: TimeoutCancellationException) {
        println("timed out with $e")
    }
}