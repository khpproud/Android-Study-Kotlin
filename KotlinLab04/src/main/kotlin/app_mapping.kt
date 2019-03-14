package fourteen_three

var list = listOf(12, 8, 9, 24)

class User(val name: String, val age: Int)

fun main() {
    // map()
    list.filter { it > 10 }
        .map { it * 10 }
        .forEach { println(it) }
    println()

    // mapIndexed()
    list.mapIndexed { index, data -> index * data }
        .forEach { println(it) }
    println()

    // groupBy()
    var list2 = listOf(User("Lee", 25), User("Shin", 19), User("Kang", 25))
    list2.groupBy { it.age }
        .forEach {
            println("key : ${it.key} ... count : ${it.value.count()}")
            it.value.forEach { it1 ->
                println("${it1.name} ... ${it1.age}")
            }
        }
}