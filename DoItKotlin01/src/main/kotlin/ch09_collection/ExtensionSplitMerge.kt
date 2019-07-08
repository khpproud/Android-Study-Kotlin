package ch09_collection

// 분리와 병합 연산 사용 예
fun main() {
    val list1 = listOf(1, 2, 3, 4, 5, 6)
    val list2 = listOf(2, 2, 3, 4, 5, 5, 6, 7)

    // union : 두 List 를 합침(중복 요소는 하나만) - set 으로 반환
    println(list1.union(list2))
    // plus : 두 List 를 합침(중복 요소 포함) '+' 연산자와 같음 - list 로 반환
    println(list1.plus(list2))

    // partition : 주어진 식에 따라 2개의 컬렉션으로 분리해 Pair 반환
    val part = list1.partition { it % 2 == 0 }
    println(part)

    // zip : 동일 인덱스 끼리 Pair 를 만들어 반환 - 요소의 개수가 가장 적은 컬렉션에 맞춰
    val zip = list1.zip(listOf(7, 8))
    println(zip)
}