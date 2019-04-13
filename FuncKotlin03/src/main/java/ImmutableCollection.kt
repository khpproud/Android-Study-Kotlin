package chap03.immutable

// 불변 리스트와 가변 리스트의 예
fun main() {
    val immutableList = listOf(1, 2, 3, 4, 5, 6, 7)
    println("ImmutableList $immutableList")
    val mutableList = immutableList.toMutableList()
    println("MutableList $mutableList")
    mutableList.add(8)
    println("추가 후의 가변 리스트 $mutableList")
    println("추가 후의 불변 리스트 $immutableList")
}