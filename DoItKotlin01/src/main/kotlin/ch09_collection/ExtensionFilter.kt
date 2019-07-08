package ch09_collection

// 컬렉션의 필터와 추출 확장함수 사용 예
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val listMixed = listOf(1, "Hello", 3, "World", 5, 'A')
    val listWithNull = listOf(1, null, 3, null, 5, 6)
    val listRepeated = listOf(2, 2, 3, 4, 5, 5, 6)
    val map = mapOf(11 to "Java", 22 to "Kotlin", 33 to "C++")

    // 특정 요소를 골라내기 filter
    println(list.filter { it % 2 == 0 })
    println(list.filterNot { it % 2 == 0 })
    println(listWithNull.filterNotNull())

    println("filterIndexed : ${list.filterIndexed { idx, value -> idx != 1 && value % 2 == 0 }}")
    // filterIndexedTo : 추출 후 가변형 콜렉션으로 변환
    val mutList = list.filterIndexedTo(mutableListOf()) { idx, value -> idx != 1 && value % 2 == 0 }
    println("filteredIndexedTo : $mutList")

    println("filterKeys : ${map.filterKeys { it != 11 }}")
    println("filterValues : ${map.filterValues { it == "Java" }}")

    // filterInstance : 여러 자료형 요소 중 원하는 자료형을 골라냄
    println("filterInstance : ${listMixed.filterIsInstance<String>()}")

    // slice : 특정 인덱스의 요소들을 잘라내서 반환
    println("slice : ${list.slice(listOf(0, 1, 2))}")

    // take : n개의 요소를 반환
    println(list.take(2))
    println(list.takeLast(2))
    println(list.takeWhile { it < 3} )

    // drop : n개의 요소를 버림
    println(list.drop(3))
    println(list.dropWhile { it < 3} )
    println(list.dropLastWhile { it > 3 })

    // componentN : 각 요소 순서의 반환
    println("component1() : ${list.component1()}")

    // distinct : 중복 요소는 하나로 취급해 컬렉션 반환
    println("distinct : ${listRepeated.distinct()}")

    // intersect : 교집합 요소만 골라냄
    println("intersect : ${list.intersect(listOf(5, 6, 7, 8))}")
}