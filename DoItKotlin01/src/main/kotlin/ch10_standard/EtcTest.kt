package ch10_standard

import kotlin.random.Random
import kotlin.system.measureTimeMillis

// 기타 함수의 사용 예
fun main() {
    val input = "Kotlin"
    val keyword = "in"

    // 입력 문자열에 키워드가 있으면 인덱스를 반환하는 함수를 takeIf() 함수를 사용해 구현
    println(input.indexOf(keyword).takeIf { it >= 0 } ?: error("keyword not found"))

    // takeUnless() 함수를 사용해 구현
    println(input.indexOf(keyword).takeUnless { it < 0 } ?: error("keyword not found"))

    // 표준 라이브러리에서 제공하는 시간 측정 함수
    val executionTime = measureTimeMillis {
        IntRange(1, 100_000).forEach { println(it) }
    }
    println("$executionTime ms")

    // 난수 생성
    val randomNumber = Random.nextInt(10)
    println(randomNumber)
}