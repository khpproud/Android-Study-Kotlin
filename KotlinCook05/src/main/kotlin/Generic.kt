package chap05

// 코틀린에서의 제네릭
// 숫자 타입만 가능하게 제한
class GenCl<T: Number>(t: T) {
    var a = t
}

fun main() {
    val intgen =  GenCl<Int> (10)
    println(intgen.a)

    fun <T> printList(a: List<T>) {
        for (x in a)
            println(x)
    }

    printList(listOf(1, 2, 3, 4, 5))

    printList(listOf("a", "b", "c", "d"))
}