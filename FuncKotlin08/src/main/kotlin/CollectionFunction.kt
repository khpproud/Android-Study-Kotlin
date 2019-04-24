package chap08

import kotlin.math.roundToInt
import kotlin.math.sqrt

// 코틀린의 컬렉션 함수 사용 예
// map
//fun main() {
//    val list = listOf<Int>(1, 2, 3, 4, 5)
//    val doubledList = list.map { it * 2 }
//
//    println(doubledList)
//}

// filter
//fun main() {
//    val list = 1.until(50).toList()
//    val filteredListEven = list.filter { it % 2 == 0 }
//    println(filteredListEven)
//
//    val filteredListSquare = list.filter {
//        val sqroot = sqrt(it.toDouble()).roundToInt()
//        sqroot * sqroot == it
//    }
//
//    println(filteredListSquare)
//
//}

// flatMap
//fun main() {
//    val list = listOf(10, 20, 30)
//    val flatMappedList = list.flatMap {
//        it.rangeTo(it + 2).toList()
//    }
//    println("flatMappedList -> $flatMappedList")
//}

// drop
//fun main() {
//    val list = 1.until(10).toList()
//    println("list.drop(5) -> ${list.drop(5)}")
//    println("list.dropLast(5) -> ${list.dropLast(5)}")
//}

// take
//fun main() {
//    val list = 1.until(10).toList()
//    println("list.take(5) -> ${list.take(5)}")
//    println("list.takeLast(5) -> ${list.takeLast(5)}")
//    println("list.takeWhile { it <= 5 } -> ${list.takeWhile { it <= 5 }}")
//    println("list.takeLastWhile { it > 5 } -> ${list.takeLastWhile { it > 5 }}")
//}

// zip - 컬렉션을 압축
//fun main() {
//    val list1 = listOf(1, 2, 3, 4, 5)
//    val list2 = listOf("Item1", "Item2", "Item3", "Item4", "Item5")
//
//    val resultZippedList = list1.zip(list2)
//
//    println(resultZippedList)
//
//    val list3 = listOf(1, 2, 3, 4, 5, 6, 7, 8)
//    val list4 = listOf("Item1", "Item2", "Item3", "Item4")
//
//    // 결합할 수 있는 쌍만큽 압축하고 나머지 아이템은 버림
//    val resultList = list3.zip(list4)
//    println(resultList)
//
//    // zipWithNext
//    val zipWithList = list3.zipWithNext()
//    println(zipWithList)
//}

// groupBy - 조건에 맞춰 그룹화한 Map을 반환
fun main() {
    val list = 1.rangeTo(50).toList()
    println(list.groupBy { it % 5 })
}
