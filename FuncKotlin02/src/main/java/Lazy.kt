package chap02.lazy

// lazy 사용 예
fun main() {
    val i by lazy {
        println("느긋하게")
        1
    }

    println("i 사용전")
    println(i)

    // 다음은 Arithmetic 에러가 발생
    //val size = listOf(2 + 1, 3 * 2, 1 / 0, 5 - 2).size

    // 람다 함수로 lazy한 계산
    val size = listOf({ 2 + 1 }, { 3 * 2 }, { 1 / 0 }, { 5 - 2 }).size

    println(size)
}