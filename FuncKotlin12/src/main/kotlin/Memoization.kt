package chap12

import arrow.syntax.function.memoize
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureNanoTime

// 메모이제이션 : 순수 함수의 결과를 캐싱
// 피보나치의 예
// 재귀
fun recursiveFib(n: Long): Long = if (n < 2) {
    n
} else {
    recursiveFib(n - 1) + recursiveFib(n - 2)
}

// 명령형 반복문
fun imperativeFib(n: Long): Long {
    return when (n) {
        0L -> 0
        1L -> 1
        else -> {
            var a = 0L
            var b = 1L
            var c = 0L
            for (i in 2.. n) {
                c = a + b
                a = b
                b = c
            }
            c
        }
    }
}

//fun main() {
//    // 람다
//    // 재귀적으로 사용하기위해 미리 선언
//    var lambdaFib: (Long) -> Long = { it }
//
//    lambdaFib = { n: Long -> if (n < 2) n else lambdaFib(n - 1) + lambdaFib(n - 2) }
//
//    // 메모이제이션
//    var memoizedFib: (Long) -> Long = { it }
//
//    memoizedFib = { n: Long -> if (n < 2) n else memoizedFib(n - 1) + memoizedFib(n - 2)}.memoize()
//
//    println(milliSeconds("명령형 피보나치") { imperativeFib(40) })
//    println(milliSeconds("재귀 피보나치") { recursiveFib(40) })
//    println(milliSeconds("람다 피보나치") { lambdaFib(40) })
//    println(milliSeconds("메모이제이션 피보나치") { memoizedFib(40) })
//
//}

// 코루틴을 사용한 실행시간 측정
fun main() = runBlocking {
    var lambdaFib: (Long) -> Long = { it }
    lambdaFib = { n: Long -> if (n < 2) n else lambdaFib(n - 1) + lambdaFib(n - 2) }

    var memoizedFib: (Long) -> Long = { it }
    memoizedFib = {
            n: Long -> println("메모이제이션 피보나치에서 n = $n")
            if (n < 2) n else memoizedFib(n - 1) + memoizedFib(n - 2)
    }.memoize()

    val job = launch {
        repeat(10) { i ->
            launch(coroutineContext) {
                println(milliSeconds("코루틴 $i - 명령형 피보나치") { imperativeFib(40) })

            }
            launch(coroutineContext) {
                println(milliSeconds("코루틴 $i - 재귀 피보나치") { recursiveFib(40) })

            }
            launch(coroutineContext) {
                println(milliSeconds("코루틴 $i - 람다 피보나치") { lambdaFib(40) })

            }
            launch(coroutineContext) {
                println(milliSeconds("코루틴 $i - 메모이제이션 피보나치") { memoizedFib(40) })

            }
        }
    }
    job.join()
}

// 함수 실행시간 측정
inline fun milliSeconds(description: String, body: () -> Unit): String =
    "$description:${measureNanoTime(body) / 1_000_000.00 } ms"