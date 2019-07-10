package ch11_coroutine

// sequence() 함수를 사용한 피보나치 수열
val fibonacciSeq = sequence {
    var a = 0
    var b = 1
    yield(1)                    // 지연 함수가 사용됨 - 시퀀스에 값을 산출

    while (true) {
        yield(a + b)            // 현재 a + b 값을 산출
        val tmp = a + b
        a = b
        b = tmp
    }
}

val seq = sequence {
    val start = 0

    yield(start)                                        // 단일 값 산출
    yieldAll(1.. 5 step 2)                      // 반복 값 산출
    yieldAll(generateSequence(8) { it * 3 })        // 무한한 시퀀스에서 산출
}

fun main() {
    println(fibonacciSeq.take(10).toList())
    println(seq.take(7).toList())

    // 시퀀스는 일회성이기 때문에 각 요소에 대해 다음 요소를 직접 지정하려면 iterator 사용
    val saved = fibonacciSeq.iterator()
    println("${saved.next()}, ${saved.next()}, ${saved.next()}")
}