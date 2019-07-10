package ch11_coroutine

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking

// actor 코루틴 빌더 사용하기
sealed class CounterMsg {
    object IncCounter : CounterMsg()                                        // counter 를 증가시키기 위한 단방향 메시지
    class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg()         // 응답 채널의 요청
}

// 새로운 counter actor 를 위한 함수
fun CoroutineScope.counterActor() = actor<CounterMsg>(coroutineContext) {
    var counter = 0                                 // actor 의 상태로 공유되지 않음
    for (msg in channel) {              // 들어오는 메시지 처리
        when (msg) {
            is CounterMsg.IncCounter -> counter++
            is CounterMsg.GetCounter -> msg.response.complete(counter)
        }
    }
}

fun main() = runBlocking<Unit> {
    val counter = counterActor()        // actor의 생성
    massiveRun {
        counter.send(CounterMsg.IncCounter)
    }
    // actor 의 counter 값을 얻기 위해 요청
    val response = CompletableDeferred<Int>()
    counter.send(CounterMsg.GetCounter(response))
    println("Counter = ${response.await()}")
    counter.close()                                             // actor 의 중단
}