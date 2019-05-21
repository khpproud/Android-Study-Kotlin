/**
 * 비트 연산 사용 예
 */
fun main() {
    val num = 42
    println(Integer.toBinaryString(num))
    println("$num shift left(2) => ${num.shl(2)} = ${toBS(num.shl(2))}")
    println("$num shift right(2) => ${num.shr(2)} = ${toBS(num.shr(2))}")
    val minusNum = -1024
    println("$minusNum ushr(2) => ${minusNum.ushr(2)} = ${toBS(minusNum.ushr(2))}")
    println("$num inv() => ${num.inv()} = ${toBS(num.inv())}")
    println("$num xor(33) => ${num.xor(33)} = ${toBS(num.xor(33))}")
    println("$num or(33) => ${num.or(33)} = ${toBS(num.or(33))}")
    println("$num and(33) => ${num.and(33)} = ${toBS(num.and(33))}")
}

private fun toBS(num: Int) = Integer.toBinaryString(num)