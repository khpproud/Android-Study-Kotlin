package chap01

// 문자열을 Long, Double, Int로 파싱하는 예
fun main() {
    val numA = "123.4"
    val numB = "123"
    println(numA.toLongOrNull())
    println(numB.toLongOrNull())

    // 2진수
    val str = "11111111"
    println(str.toLongOrNull(2))
}