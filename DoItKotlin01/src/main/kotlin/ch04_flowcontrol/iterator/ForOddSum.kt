package ch04_flowcontrol.iterator

// 1 ~ 100의 홀수 합 구하기
fun main() {
    var total: Int = 0
    for (odd in 1 until 100 step 2) total += odd
    println("Odd Total : $total")

    for (even in 0.. 100 step 2) total += even
    println("1 ~ 100 sum total : $total")
}