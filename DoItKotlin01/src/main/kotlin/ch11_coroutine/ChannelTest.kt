package ch11_coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// send() receive() 함수로 채널 사용 예
//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        // 여기에 다량의 CPU 연산 작업이나 비동기 로직을 둘 수 있음
//        for (x in 1.. 5)
//            channel.send(x * x)
//    }
//    repeat(5) {
//        println(channel.receive())          // 5개의 값을 채널로부터 받음
//    }
//    println("Done!")
//}

// close() 사용 - 채널을 닫겠다는 특수한 토큰을 보냄
//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1.. 5)
//            channel.send(x * x)
//        channel.close()                         // 모두 보내고 닫기 명시
//    }
//    for (e in channel)                      // for 문을 사용해 끝까지 읽기
//        println(e)
//    println("Done!")
//}

// offer() 와 receive() 사용
fun main() = runBlocking {
    val channel = Channel<Int>(capacity = Channel.BUFFERED)
    launch {
        for (x in 1.. 5)
            channel.offer(x * x)
    }
    repeat(5) {
        println(channel.receive())
    }
    channel.close()
    println("Done!")
}