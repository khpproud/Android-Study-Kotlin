package chap06

// 원본 컬렉션을 컬렉션 쌍으로 분할
fun main() {
    val listA = listOf(1, 2, 3, 4, 5, 6)
    val pair = listA.partition {
        it % 2 == 0
    }
    println(pair)
}