package chap07

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce

// 시퀀스나 스트림을 보내려는 경우 채널 사용
//fun main() = runBlocking {
//    val channel = Channel<String>()
//
//    val world = launch {
//        delay(500)
//        channel.send("World (채널을 사용한 또 다른 코루틴에서)")
//    }
//
//    val hello = launch {
//        println("Hello ${channel.receive()}")
//    }
//
//    hello.join()
//    world.join()
//}

// 100만개의 코루틴 예제
//fun main() = runBlocking {
//    val channel = Channel<Char>()
//
//    val jobs = List(1_000_000) {
//        launch {
//            delay(1000)
//            channel.send('.')
//        }
//    }
//    repeat(1_000_000) { print(channel.receive()) }
//    jobs.forEach { it.join() }
//}

// 단일 코루틴은 채널로 메시지를 보냄
//fun main() = runBlocking {
//    val channel = Channel<Char>()
//
//    val sender = launch {
//        repeat(1000) {
//            delay(10)
//            channel.send('.')
//            delay(10)
//            channel.send(',')
//        }
//        channel.close()
//    }
//
//    for (msg in channel) {
//        print(msg)
//    }
//
//    sender.join()
//}

// 위의 코드를 더 간단하게 produce 빌더를 사용
fun dotsAndCommas(size: Int) = GlobalScope.produce {
    repeat(size) {
        delay(10)
        send('.')
        delay(10)
        send(',')
    }
}

fun main() = runBlocking {
    val channel = dotsAndCommas(100)

    for (msg in channel)
        print(msg)
}