package ch08_generic

typealias arithmetic<T> = (T, T) -> T

// 람다식에서 제네릭 사용
fun <T> add(a: T, b: T, op: (T, T) -> T): T {
    return op(a, b)
}

fun <T> multi(a: T, b: T, op: arithmetic<T>): T {
    return op(a, b)
}

fun main() {
    val result = add(2, 3) { a, b -> a + b }
    println(result)

    val result2 = multi(3, 4) { a, b -> a * b }
    println(result2)
}