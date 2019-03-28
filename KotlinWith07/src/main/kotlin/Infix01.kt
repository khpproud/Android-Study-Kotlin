package seven.infix

// to 함수 - 중위 확장 함수로도 만들 수 있음
infix fun <T> List<T>.intersection(other: List<T>) = filter { it in other }

fun main() {
    println(1 to 2 == Pair(1, 2))

    println(listOf(1, 2, 3) intersection listOf(2, 3, 4))           // [2, 3]
}
