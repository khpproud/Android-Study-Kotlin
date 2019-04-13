package chap03.immutable

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 스레드에 안전한 데이터 변경
 */

class MyDataImmutable {
    val someData = 0
}

fun main() {
    val myData = MyDataImmutable()

    GlobalScope.launch {
        var someDataCopy = myData.someData
        for (i in 11.. 20) {
            someDataCopy += i
            println("1번째 async 에서의 someData $someDataCopy")
            delay(500)
        }
    }

    GlobalScope.launch {
        var someDataCopy = myData.someData
        for (i in 1.. 10) {
            someDataCopy++
            println("2번째 async 에서의 someData $someDataCopy")
            delay(300)
        }
    }

    runBlocking { delay(10000) }
}