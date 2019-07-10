package ch10_standard

// with() 함수 사용 예
fun main() {
    data class User(val name: String, var skills: String, var email: String? = null)
    val user = User("Koo", "default")

    val result = with (user) {
        skills = "Kotlin"
        email = "koo@goo.com"
    }
    println(user)
    println("result : $result")
}