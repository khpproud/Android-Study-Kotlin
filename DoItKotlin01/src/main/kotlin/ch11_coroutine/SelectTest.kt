package ch11_coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import java.util.*

// select 표현식 이용해보기
fun main() = runBlocking {
    val routine1 = GlobalScope.produce {
        delay(Random().nextInt(1000).toLong())
        send("A")
    }
    val routine2 = GlobalScope.produce {
        delay(Random().nextInt(1000).toLong())
        send("B")
    }
    val result = select<String> {               // 먼저 수행된 것을 받음
        routine1.onReceive { result -> result }
        routine2.onReceive { result -> result }
    }
    println("Result was $result")
}