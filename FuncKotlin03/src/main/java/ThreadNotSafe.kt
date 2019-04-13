package chap03.immutable

import kotlinx.coroutines.*;

/**
 * 스레드에 안전하지 않은 데이터 변경 예
 */
class MyData {
    var someData:Int = 0
}

fun main() {
    val myData = MyData()

    GlobalScope.async {
        for (i in 11.. 20) {
            myData.someData += i
            println("1번째 async로 부터의 someData ${myData.someData}")
            delay(500)
        }
    }

    GlobalScope.async {
        for (i in 1.. 10) {
            myData.someData++
            println("2번째 async로 부터의 someData ${myData.someData}")
            delay(300)
        }
    }

    runBlocking { delay(10000) }
}