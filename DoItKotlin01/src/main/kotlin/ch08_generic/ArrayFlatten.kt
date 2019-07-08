package ch08_generic

// 배열 평탄화 하기
fun main() {
    val numbers = arrayOf(1, 2, 3)
    val strs = arrayOf("one", "two", "three")
    val simpleArray = arrayOf(numbers, strs)
    simpleArray.forEach { println(it) }

    val flattenSimpleArray = simpleArray.flatten()
    println(flattenSimpleArray)

    var s1 = "Hello kotlin"
    var s2 = "Hello KOTLIN"

    println(s1.compareTo(s2))
    println(s1.compareTo(s2, true))
}