package ch02_val_type_op

fun main() {
    val a: Int = 128
    val b = a
    println(a === b)

    val c: Int? = a
    val d: Int? = a
    val e: Int? = c

    println(c == d)
    println(c === d)                    // a의 값이  -127 ~ 128 범위 내일 경우 캐시에 저장되므로 같은 주소를 가리키게 됨
    println(c === e)
}