package ch11_coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 버퍼를 가진 채널 구성
fun main() = runBlocking {
    val channel = Channel<Int>(3)
    val sender = launch(coroutineContext) {
        repeat(10) {
            println("Sending $it")
            channel.send(it)                    // 지속적으로 보내다 꽉 차면 일시 지연
        }
    }
    delay(1000L)                        // 아무것도 받지 않고 1초 기다린 후
    sender.cancel()                             // 송신자의 작업을 취소
}