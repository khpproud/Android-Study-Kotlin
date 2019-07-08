package ch09_collection

import java.util.*

// TreeSet 의 초기화
fun main() {
    val intsSortedSet: TreeSet<Int> = sortedSetOf(4, 1, 7, 3)
    intsSortedSet.add(6)
    intsSortedSet.remove(1)
    println("intsSortedSet = $intsSortedSet")

    intsSortedSet.clear()
    println("intsSortedSet = $intsSortedSet")
}