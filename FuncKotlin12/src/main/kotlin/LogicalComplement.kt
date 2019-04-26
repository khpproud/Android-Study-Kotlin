package chap12

import arrow.core.Predicate
import arrow.syntax.function.complement


// 논리 부정 : 모든 술어(Predicate)를 받아 이를 부정
fun main() {
    val evenPredicate: Predicate<Int> = { i: Int -> i % 2 == 0 }
    val oddPredicate: (Int) -> Boolean = evenPredicate.complement()

    val numbers: IntRange = 1.. 10
    val evenNumbers: List<Int> = numbers.filter(evenPredicate)
    val oddNumbers: List<Int> = numbers.filter(oddPredicate)

    println(evenNumbers)
    println(oddNumbers)
}