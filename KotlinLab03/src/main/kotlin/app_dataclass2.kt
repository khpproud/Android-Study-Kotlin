package eleven_one_two

class Product(val name: String, val price: Int)

data class User(val name: String, val age: Int) {
    var email: String = "a@a.com"
}

fun main() {
    // toString()
    var product = Product("prod1", 100)
    println(product.toString())

    var user = User("Park", 35)
    println(user.toString())

    // componentN()
    var user1 = User("Kim", 20)

    println(user1.component1())
    println(user1.component2())

    // 분해 선언
    var user2 = User(age = 30, name = "Song")
    val (name, age) = user2

    println("name : $name, age : $age")

    // copy() 함수
    var user3 = User(age = 19, name = "Choi")
    println(user3.toString())

    var user4 = user3.copy(name = "Chang")
    println(user4.toString())
}