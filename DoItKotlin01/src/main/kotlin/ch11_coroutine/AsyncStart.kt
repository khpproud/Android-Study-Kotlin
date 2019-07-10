package ch11_coroutine

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

//// 시작 시점 늦춰보기
//suspend fun doWork3(): String {
//    delay(1000)
//    return "Work3"
//}
//suspend fun doWork4(): String {
//    delay(3000)
//    return "Work4"
//}
//
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async(start = CoroutineStart.LAZY) { doWork3() }
//        val two = async(start = CoroutineStart.LAZY) { doWork4() }
//        println("AWAIT: ${one.await()}_${two.await()}")
//    }
//    println("Completed in $time ms")
//}