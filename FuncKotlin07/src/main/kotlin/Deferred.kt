package chap07

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 두 코루틴이 통신할 수 있는 방법 중 Deferred<T> 사용 예
fun main() = runBlocking {
    val result = CompletableDeferred<String>()

    val world = launch {
        delay(500)
        result.complete("World (또 다른 코루틴에서)")
    }

    val hello = launch {
        println("Hello ${result.await()}")
    }

    hello.join()
    world.join()
}