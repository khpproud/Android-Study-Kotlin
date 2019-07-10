package ch11_coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 많은 양의 작업 생성하기
fun main() = runBlocking {
//    val jobs = List(100_000) {// 많은 양의 코루틴을위한 List
//        launch {
//            delay(1000L)
//            print(".")
//        }
//    }
//    jobs.forEach { it.join() }

    // repeat 사용하여 많은 여의 코루틴 생성 가능
    repeat(100_000) {
        launch {
            delay(1000L)
            print("#")
        }
    }
}