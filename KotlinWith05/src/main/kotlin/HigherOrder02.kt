package five.higher.two

import java.math.BigDecimal

// 상위 함수 사용 예
// accumulator 함수를 인수로 받아 sum. prod 함수를 리턴
fun sum(numbers: List<BigDecimal>) = fold(numbers, BigDecimal.ZERO) { acc, num -> acc + num }

fun prod(numbers: List<BigDecimal>) = fold(numbers, BigDecimal.ONE) { acc, num -> acc * num }

private fun fold(numbers: List<BigDecimal>, start: BigDecimal,
                 accumulator: (BigDecimal, BigDecimal) -> BigDecimal): BigDecimal {
    var acc = start
    for (num in numbers) {
        acc = accumulator(acc, num)
    }
    return acc
}

fun main() {
    fun BD(i : Long) = BigDecimal.valueOf(i)
    val numbers = listOf(BD(1), BD(2), BD(3), BD(4))
    println(sum(numbers))
    println(prod(numbers))
}