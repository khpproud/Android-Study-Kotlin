package five.higher

import java.math.BigDecimal

// 상위 함수 사용 않는 예
fun sum(numbers: List<BigDecimal>): BigDecimal {
    var sum = BigDecimal.ZERO
    for (num in numbers) {
        sum += num
    }
    return sum
}

fun prod(numbers: List<BigDecimal>): BigDecimal {
    var prod = BigDecimal.ONE
    for (num in numbers) {
        prod *= num
    }
    return prod
}

val numbers = listOf(
    BigDecimal.TEN,
    BigDecimal.ONE,
    BigDecimal.valueOf(2)
)

fun main() {
    println(numbers)
    println(prod(numbers))
    println(sum(numbers))
}