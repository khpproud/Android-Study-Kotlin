package chap07

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.firstOrNull
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

// 변경 가능한 상태 관리 의 예
// 카운터를 업데이트
suspend fun repeatInParallel(times: Int, block: suspend () -> Unit) {
    val job = GlobalScope.launch {
        repeat(times) {
            launch(coroutineContext) {
                block()
            }
        }
    }
    job.join()
}

// 이상한 숫자가 표시됨...
//fun main() = runBlocking {
//    var counter = 0
//    val time = measureTimeMillis {
//        repeatInParallel(1_000_000) {
//            counter++
//        }
//    }
//    println("counter = $counter")
//    println("time = $time")
//}

// 컨텍스트 전환으로 문제 해결 - 업데이트 작업을 다른 컨텍스트에서 처리
//fun main() = runBlocking {
//    var counter = 0
//
//    val counterContext = newSingleThreadContext("CounterContext")
//
//    val time = measureTimeMillis {
//        repeatInParallel(1_000_000) {
//            // withContext 함수는 특정 코루틴 컨텍스트 블록을 실행
//            withContext(counterContext) {
//                counter++
//            }
//        }
//    }
//    println("counter = $counter")
//    println("time = $time")
//}

// 스레드 안전 구조체(자바 5 이상에서 아토믹 스레드 안전 구초제에 접근)로 처리
//fun main() = runBlocking {
//    val counter = AtomicInteger(0)
//    val time = measureTimeMillis {
//        repeatInParallel(1_000_000) {
//            counter.incrementAndGet()
//        }
//    }
//    println("counter = ${counter.get()}")
//    println("time = $time")
//}

// 뮤텍스(Mutex) 여러 코루틴이 같은 리소스를 공유하지만 동시에는 접근하지 못하게 함
//fun main() = runBlocking {
//    val mutext = Mutex()
//    var counter = 0
//
//    val time = measureTimeMillis {
//        repeatInParallel(1_000_000) {
//            mutext.withLock {
//                counter++
//            }
//        }
//    }
//    println("counter = $counter")
//    println("timr = $time")
//}

// 액터(Actor) 메시지를 통해 외부 월드 및 다른 액터와 상호작용하는 오브젝트
// 액터를 작성하려면 우선 보내려는 메시지를 만들어야 함
sealed class CounterMsg
object IncCounter: CounterMsg()
// GetCounter는 액터 밖의 카운터 값을 알려주는 CompletableDeferred<Int> 값을 가짐
class GetCounter(val response: CompletableDeferred<Int>): CounterMsg()

// 액터를 만들기 위해 actor<CounterMsg> 빌더를 사용 - SendChannel<CounterMsg>를 반환
fun counterActor(start: Int) = GlobalScope.actor<CounterMsg> {
    var counter = start
    for (msg in channel) {
        when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

fun main() = runBlocking {
    val counterActor = counterActor(0)

    val time = measureTimeMillis {
        repeatInParallel(1_000_000) {
            counterActor.send(IncCounter)
        }
    }

    val counter = CompletableDeferred<Int>()
    counterActor.send(GetCounter(counter))
    println("counter = ${counter.await()}")
    println("time = $time")
}