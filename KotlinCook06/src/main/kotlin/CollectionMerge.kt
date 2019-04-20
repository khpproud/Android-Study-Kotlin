package chap06

// 두 개의 컬렉션을 병합하는 방법
fun main() {
    var listA = mutableListOf("a", "a", "b")
    var listB = mutableListOf("a", "c")
    listB.addAll(listA)
    println(listB)

    // union : 중복된 값을 제거
    listA = mutableListOf("a", "a", "b")
    listB = mutableListOf("a", "c")
    var listC = listB.union(listA)
    println(listC)

    // 두 개의 맵을 병합하려면 putAll 사용
    val mapA = mutableMapOf("a" to 1, "b" to 2)
    val mapB = mutableMapOf("a" to 3, "c" to 5)
    mapA.putAll(mapB)
    println(mapA)
}