package fourteen_five

fun main() {
    // reversed()
    listOf(2, 6, 3, 7).reversed()
        .forEach { println(it) }
    println()

    // sorted() - 숫자 정렬
    listOf(1, 6, 3, 2).sorted()
        .forEach { println(it) }
    println()

    // sorted() - 문자 정렬
    listOf("Park", "Lee", "Kim").sorted()
        .forEach { println(it) }
    println()

    // sortedBy()
    listOf(1, 5, 3).sortedBy { it % 3 }
        .forEach { println(it) }
    println()

    // sortedDescending()
    listOf("lee", "kang", "park").sortedDescending()
        .forEach { println(it) }
}