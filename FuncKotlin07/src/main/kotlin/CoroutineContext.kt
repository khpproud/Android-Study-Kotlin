package chap07

import kotlinx.coroutines.*

// 코루틴 컨텍스트의 개념
fun main() = runBlocking {
    println("coroutineContext 블록 실행 = $coroutineContext")
    println("coroutineContext[Job] = ${coroutineContext[Job]}")
    println(Thread.currentThread().name)
    println("---------------")

    val jobs = listOf(
        launch {
            println("launch coroutineCoxtext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("---------------")
        },
        async {
            println("async coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("---------------")
        },
        launch(Dispatchers.Default) {
            println("default launch coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("---------------")
        },
        launch(Dispatchers.IO) {
            println("IO launch coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("---------------")
        },
        launch(coroutineContext) {
            println("inherit launch coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("---------------")
        }
    )

    jobs.forEach { job ->
        println("job = $job")
        job.join()
    }
}

// 코루틴 컨텍스트는 자식을 제어하는데 사용할 수도 있다
//fun main() = runBlocking {
//    val job = launch {
//        repeat(1_000_000) {
//            launch(coroutineContext) {
//                delay(1000)
//                print('.')
//            }
//        }
//    }
//}