package four

fun main() {
    // 중위 호출
    var pair = "Everest" to 8848
    println(pair)
    // 아래와 동일
    val moutain = "Everest"
    pair = moutain.to(8848)
    println(pair)

    println(pair.first)
    println(pair.second)
}