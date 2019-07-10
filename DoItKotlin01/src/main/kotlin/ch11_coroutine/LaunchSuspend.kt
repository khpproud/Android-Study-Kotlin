package ch11_coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors

// 코루틴의 순차적 실행
suspend fun doWork1(): String {
    delay(1000)
    return "Work1"
}

suspend fun doWork2(): String {
    delay(3000)
    return "Work2"
}

private fun worksIsSerial() {
    // 순차적 실행
    GlobalScope.launch {
        val one = doWork1()
        val two = doWork2()
        println("Kotlin One : $one")
        println("Kotlin Two : $two")
    }
}

private fun worksInParallel() {
    // Deferred<T> 를 통해 결과 값 반환
    val one = GlobalScope.async {
        doWork1()
    }
    val two = GlobalScope.async {
        doWork2()
    }
    GlobalScope.launch {
        val combined = one.await() + "_" + two.await()
        println("Kotlin Combined : $combined")
    }
}

private fun worksInMyContext() {
    // 스레드 개수를 직접 지정하여 사용자 문맥을 만듬
    val threadPool = Executors.newFixedThreadPool(1)
    val myContext = threadPool.asCoroutineDispatcher()
    GlobalScope.async(myContext) {
        println("do my context")
    }
}

private fun worksInLazy() {
    // 코루틴을 지연 시작
    val job = GlobalScope.async(start = CoroutineStart.LAZY) {
        println(doWork1())
    }
    job.start()                         // 실제 시작 시점으로 또는 job.await()으로 시작됨

}

fun main() {
    worksIsSerial()
    worksInParallel()
    worksInMyContext()
    worksInLazy()
    Thread.sleep(5000)
}