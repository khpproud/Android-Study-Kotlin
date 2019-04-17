package chap02

// 다중 조건 반복문을 코틀린에서 표현하는 방법
val numbers = arrayOf(5, 6, 7, 1, 2, 4, 5, 7, 12, 13)

fun main() {
    // 리스트 생성을 즉시 처리함 - takeWhile 조건을 만족할 때까지 배열 순회
    (0.. 9).takeWhile {
        println("Inside takeWhile $it")
        it < numbers[it]
    }.forEach {
        println("Inside forEach")
        println("$it - ${numbers[it]}")
    }

    // asSequence 로 지연 처리 - takeWhile 이 한꺼번에 처리되지 않고 forEach 구문에서 다음 요소를 필요할때 검증하고 처리
    (0.. 9).asSequence().takeWhile {
        println("Inside takeWhile $it")
        it < numbers[it]
    }.forEach {
        println("Inside forEach")
        println("$it - ${numbers[it]}")
    }
}