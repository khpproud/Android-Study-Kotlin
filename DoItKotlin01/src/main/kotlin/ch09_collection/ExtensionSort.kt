package ch09_collection

// 순서와 정렬 연산 사용하기
fun main() {
    // reversed : 뒤집힌 순서로 컬렉션 반환
    val unsortedList = listOf(3, 2, 7, 5, 4, 9, 6)
    println(unsortedList.reversed())

    // sorted : 요소를 정렬된 컬렉션 반환
    println(unsortedList.sorted())

    // sortedDescending : 내림차순 정렬
    println(unsortedList.sortedDescending())

    // sortedBy : 특정 비교식에 의해 정렬된 컬렉션 반환
    println(unsortedList.sortedBy { it % 3 })
    println(unsortedList.sortedByDescending { it % 3 })
}