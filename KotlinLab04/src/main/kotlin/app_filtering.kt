package fourteen_two

val list = listOf(12, 9, 4, 25, 17)
val map = mapOf("one" to 15, "two" to 5)

fun main() {
    list.filter { it > 10 }
        .forEach { println(it) }

    // Map에서의 filter 활용
    map.filter { entry -> entry.value > 10 }
        .forEach { println(it) }

    // filterNot()
    listOf(21, 1, 12, 5, 25).filterNot { it > 10 }
        .forEach { println(it) }

    // filterNotNull()
    listOf<Any?>(1, null, "Hello", 35).filterNotNull()
        .forEach { println(it) }

    // drop()
    list.drop(2)
        .forEach { println(it) }

    // dropWhile()
    list.dropWhile { it < 20 }
        .forEach { println(it) }

    // dropLastWhile()
    list.dropLastWhile { it > 10 }
        .forEach { println(it) }
    println()

    // slice()
    listOf(1, 12, 5, 23, 5, 16).slice(1 .. 3)
        .forEach { println(it) }
    println()

    listOf(1, 12, 5, 23, 7, 19).slice(listOf(0, 2, 4))
        .forEach { println(it) }
    println()

    // take()
    list.take(3)
        .forEach { println(it) }
    println()

    // takeLast()
    list.takeLast(3)
        .forEach { println(it) }
    println()

    // takeWhile()
    listOf(11, 12, 13, 4, 5, 19).takeWhile { it > 10 }
        .forEach { println(it) }

}