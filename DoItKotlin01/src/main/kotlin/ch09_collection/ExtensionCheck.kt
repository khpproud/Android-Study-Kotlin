package ch09_collection

// 컬렉션의 검사 확장 함수 사용 예
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val listPair = listOf(Pair("A", 300), Pair("B", 200), Pair("C", 100))
    val map = mapOf(11 to "Java", 22 to "Kotlin", 33 to "C++")

    // 요소의 일치 여부 검사 all
    println(list.all { it < 10 })
    println(list.all { it % 2 == 0 })

    println(list.any { it % 2 == 0 })
    println(list.any { it > 10 })

    // 특정 요소의 포함 및 존재 여부 검사 contains
    println("contains : ${list.contains(2)}")
    println(2 in list)
    println(map.contains(11))
    println(11 in map)

    // containsAll : 모든 요소가 포함되어 있으면 true 를 반환
    println("containsAll : ${list.containsAll(listOf(1, 2, 3))}")

    // none : 요소가 없으면 true, 있으면 false
    println("none : ${list.none()}")
    println("none : ${list.none { it > 6 }}")

    // isEmpty/isNotEmpty : 컬렉션이 비어있는지 아닌지 여부
    println(list.isEmpty())
    println(list.isNotEmpty())
}