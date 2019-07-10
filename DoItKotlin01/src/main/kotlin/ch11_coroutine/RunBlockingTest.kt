package ch11_coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// main()을 블로킹 모드로 동작
//fun main() = runBlocking<Unit> {
//    launch {            // 백그라운드로 코루틴 실행
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello, ")
////    delay(2000L)                      // delay() 함수를 사용하지 않아도 코루틴을 기다림
//}

// 다음과 같이 main 에 suspend 지정 가능
//suspend fun main() = coroutineScope {
//    launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello")
//}

// 명시적으로 join() 함수의 결과 기다리기
fun main() = runBlocking {
    val job = launch {
        delay(1000L)
        println("Kotlin!")
    }
    println("Hello")
    job.join()
}