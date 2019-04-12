package chap02.resursion

// 일반적인 명령형 구현, 루프 및 상태 변경
fun factorial(n: Long): Long {
    var result = 1L
    for (i in 1.. n) {
        result *= i
    }
    return result
}

// 루프와 상태 변경이 없는 재귀
fun functionalFactorial(n: Long): Long {
    fun go(n: Long, acc: Long): Long {
        return if (n <= 0) {
            acc
        } else {
            go(n - 1, n * acc)
        }
    }
    return go(n, 1)
}

// tailrec 수정자 사용
fun tailrecFactorial(n: Long): Long {
    tailrec fun go(n: Long, acc: Long): Long {
        return if (n <= 0) {
            acc
        } else {
            go(n - 1, n * acc)
        }
    }
    return go(n, 1)
}

// 간단한 Profiler
fun executionTime(body: () -> Unit): Long {
    val startTime = System.nanoTime()
    body()
    val endTime = System.nanoTime()
    return endTime - startTime
}

fun main() {
    println("factorial : " + executionTime { factorial(20) })
    println("functionalFactorial : " + executionTime { functionalFactorial(20) })
    println("tailrecFactorial : " + executionTime { tailrecFactorial(20) })
}