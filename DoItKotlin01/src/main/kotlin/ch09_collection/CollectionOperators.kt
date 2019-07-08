package ch09_collection

// 컬렌션으로 연산 테스트
fun main() {
    val list1 = listOf("one", "two", "three")
    val list2 = listOf(1, 3, 4)
    val map1 = mapOf("hi" to 1, "hello" to 2, "Goodbye" to 3)

    println(list1 + "four")                 // + 연산자를 사용한 문자열 요소 추가
    println(list2 + 1)
    println(list2 + listOf(5, 6, 7))        // 두 리스트의 병합
    println(list2 - 1)                      // 요소의 제거
    println(list2 - listOf(3, 4, 5))        // 일치하는 요소의 제거
    println(map1 + Pair("Bye", 4))          // Pair 를 사용한 Map 요소의 추가
    println(map1 - "hello")                 // 일치하는 값의 제거
    println(map1 + mapOf("Apple" to 4, "Orange" to 5))
    println(map1 - listOf("hi", "hello"))   // List 에 일치하는 값을 Map 에서 제거
}