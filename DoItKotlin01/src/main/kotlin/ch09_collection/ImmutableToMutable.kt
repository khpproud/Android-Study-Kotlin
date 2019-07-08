package ch09_collection

// 불변형 리스트를 가변형으로 변환
fun main() {
    val names = listOf("One", "Two", "Three")
    val mutableNames = names.toMutableList()
    mutableNames.add("Four")
    println(mutableNames)
}