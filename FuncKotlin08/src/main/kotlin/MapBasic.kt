package chap08

// 맵 컬렉션 예
fun main() {
    val map = mapOf(
        "One" to 1,
        "Two" to 2,
        "Three" to 3,
        "Four" to 4,
        "Five" to 0,
        "Six" to 6,
        "Five" to 5
    )

    println("키 'Four'의 값은 ${map["Four"]} 이다")

    println("map의 내용")
    for (entry in map) {
        println("Key ${entry.key}, Value ${entry.value}")
    }

    val mutableMap = mutableMapOf<Int, String>()
    mutableMap[1] = "Item1"
    mutableMap[2] = "Item2"
    mutableMap[3] = "Item3"
    mutableMap[4] = "Item4"

    println("키 1의 값 변경 - ${mutableMap.put(1, "Item5")}")

    println("mutableMap의 내용")
    for (entry in mutableMap)
        println("Key ${entry.key} Value ${entry.value}")
}