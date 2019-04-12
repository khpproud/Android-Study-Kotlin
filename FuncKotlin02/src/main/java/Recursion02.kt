package chap02.resursion

// 명령형으로 구현된 피보나치
fun fib(n: Long): Long {
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

// 재귀적으로 구현한 피보나치
fun functionalFib(n: Long): Long {
    fun go(n: Long, prev: Long, cur: Long): Long {
        return if (n == 0L)
            prev
        else
            go(n - 1, cur, prev + cur)
    }
    return go(n, 0, 1)
}

// 꼬리재귀 버전 피보나치
fun tailrecFib(n: Long): Long {
    tailrec fun go(n: Long, prev: Long, cur: Long): Long {
        return if (n == 0L)
            prev
        else
            go(n - 1, cur, prev + cur)
    }
    return go(n, 0, 1)
}

fun main() {
    println("fib : " +  executionTime { fib(100) })
    println("functionalFib : " + executionTime { functionalFib(100) })
    println("tailrec : " + executionTime { tailrecFib(100) })
}