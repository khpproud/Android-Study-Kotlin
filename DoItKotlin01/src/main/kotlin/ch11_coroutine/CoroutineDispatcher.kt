package ch11_coroutine

import kotlinx.coroutines.*

// 실행 문맥 지정하기
@UseExperimental(ObsoleteCoroutinesApi::class)
fun main() = runBlocking {
    val jobs = arrayListOf<Job>()
    jobs += launch(Dispatchers.Unconfined) {    // 메인 스레드에서 작업
        println("Unconfined:\t\t ${Thread.currentThread().name}")
    }
    jobs += launch(coroutineContext) {          // 부모의 문맥, 여기서는 runBlocking 의 문맥
        println("coroutineContext:\t ${Thread.currentThread().name}")
    }
    jobs += launch(Dispatchers.Default) {       // Dispatcher 의 기본 값
        println("Default:\t\t ${Thread.currentThread().name}")
    }
    jobs += launch(Dispatchers.IO) {            // 입축력 중심의 문맥
        println("IO:\t\t ${Thread.currentThread().name}")
    }
    jobs += launch {                            // 아무런 인자가 없을 때
        println("main runBlocking: ${Thread.currentThread().name}")
    }
    jobs += launch(newSingleThreadContext("MyThread")) {    // 새 스레드 생성
        println("MyThread:\t\t ${Thread.currentThread().name}")
    }
    jobs.joinAll()
}