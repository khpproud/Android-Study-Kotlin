package chap04

// 함수에서 Pair를 사용해 두 개의 값 반환
fun getUser(): Pair<Int, String> {
    return Pair(1, "Blah!")
}

fun main() {
    val (userId, userName) = getUser()
    println("User Id : $userId, User name : $userName")
}