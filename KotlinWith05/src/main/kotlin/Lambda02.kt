package five.lambda

// 람다식의 구조 분해 예
data class User(val name: String, val surname: String, val phone: String)

val showUser: (User) -> Unit = { (name, surname, phone) ->
    println("$name $surname have phone number : $phone")
}

val user = User("Bart", "Simpson", "+48 123 456 789")

fun main() {
    showUser(user)

    // map의 구조 분해
    val map = mapOf(1 to 2, 2 to "A")
    val text = map.map { (key, value) -> "$key : $value" }
    println(text)

    // pair의 구조 분해
    val listOfPairs = listOf(1 to 2, 2 to "A")
    val text2 = listOfPairs.map { (first, second) -> "$first and $second" }
    println(text2)
}