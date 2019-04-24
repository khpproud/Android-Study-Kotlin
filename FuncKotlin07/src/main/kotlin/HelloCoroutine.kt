package chap07

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// coroutine 사용 예
//fun main() {
//    // 이 함수는 코루틴을 생성한 후 코루틴이 끝나고 값을 반환할 때까지 현재 스레드를 블록시킴
//    runBlocking {
//        // 이 함수는 현재 스레드의 블록없이 새 코루틴을 생성하고 Job을 반환(여기서는 무시됨)
//        launch {
//            // 현재 스레드를 블록하지 않고 현재 코루틴을 지연
//            delay(1000)
//            println("Hello by ${Thread.currentThread().name}")
//        }
//        println("Hello by ${Thread.currentThread().name}")
//        delay(2000)
//    }
//}

// 각기 다른 실행시간의 계산 : launch에 의해 생성된 job에 대한 참조를 얻고 일시 중지 함수 join에서 일시 중지함
//fun main() {
//    runBlocking {
//        val job = launch {
//            delay(1000)
//            println("Hello")
//        }
//        println("Hello")
//        job.join()
//    }
//}

// Task가 10000개 일 때
fun main() {
    runBlocking {
        val jobs = List(10_000) {
            launch {
                delay(1000)
                print('.')
            }
        }
        jobs.forEach { it.join() }
    }
}