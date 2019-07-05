package ch03_function

import java.math.BigInteger

// 피보나치 수열을 꼬리 재귀로 만들기
fun main() {
    val n = 40
    val first = BigInteger("0")
    val second = BigInteger("1")

    println("fibonacci $n = ${fibonacci(n, first, second)}")

    println("fibo $n = ${fibo(n)}")
}

tailrec fun fibonacci(n: Int, a: BigInteger, b: BigInteger): BigInteger {
    return if (n == 0) a else fibonacci(n - 1, b, a + b)
}

// 일반 적인 재귀 : n이 30만 넘어가도 계산이 기하급수적으로 느려짐
fun fibo(n: Int): BigInteger {
    return when (n) {
        0 -> BigInteger("0")
        1 -> BigInteger("1")
        else -> fibo(n - 2) + fibo(n - 1)
    }
}