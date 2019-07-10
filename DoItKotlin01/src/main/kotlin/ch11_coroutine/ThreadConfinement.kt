package ch11_coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

// 스레드에 가두기
// 단일 스레드 문맥을 선언
val counterContext = newSingleThreadContext("CounterContext")
var counter1 = 0

suspend fun massiveRun(context: CoroutineContext, action: suspend () -> Unit) {
    val n = 1000
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n) {
            GlobalScope.launch(context) {
                repeat(k) {
                    action()
                }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} action is $time ms")
}

// 문맥상 counter1를 독립적으로 가지며 처리
fun main() = runBlocking {
    massiveRun(counterContext) {
//        withContext(counterContext) {                   // 단일 스레드에 가둠
//            counter1++
//        }
        counter1++
    }
    println("Counter = $counter1")
}