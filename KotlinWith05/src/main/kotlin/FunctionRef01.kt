package five.ref

// 함수 참조 예(Reflection)
fun greet() {
    print("Hello! ")
}

fun salute() {
    print("Have a nice day~ \n")
}

val todoList: List<() -> Unit> = listOf(::greet, ::salute, ::salute)

fun isOdd(i : Int) = i % 2 == 1

val predicate: (Int) -> Boolean = ::isOdd

// 메서드 참조 예
val isStringEmpty: (String) -> Boolean = String::isEmpty

object MathHelpers {
    fun isEven(i: Int) = i % 2 == 0
}

class Math {
    companion object {
        fun isOdd(i: Int) = i % 2 == 1
    }
}

fun main() {
    for (task in todoList)
        task()

    val annotations = ::isOdd.annotations
    val parameters = ::isOdd.parameters
    println(annotations.size)
    println(parameters.size)

    println(predicate(5))

    val nonEmpty = listOf("A", "", "B", "").filter(String::isNotEmpty)
    println(nonEmpty)

    val evenPredicate: (Int) -> Boolean = MathHelpers::isEven
    val oddPredicate: (Int) -> Boolean = Math.Companion::isOdd
    val numbers = 1.. 10
    val even = numbers.filter(evenPredicate)
    val odd = numbers.filter(oddPredicate)
    println(even)
    println(odd)
}