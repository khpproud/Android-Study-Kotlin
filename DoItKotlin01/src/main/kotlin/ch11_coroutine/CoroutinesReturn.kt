package ch11_coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Job 객체의 반환
//fun main() {
//    val job = GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello, ")
//    println("job.isActive: ${job.isActive}, completed: ${job.isCompleted}")
//    Thread.sleep(2000L)
//    println("job.isActive: ${job.isActive}, completed: ${job.isCompleted}")
//}